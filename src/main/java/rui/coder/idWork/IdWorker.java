package rui.coder.idWork;

import lombok.Builder;

public class IdWorker {

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;


    private final long sequenceMask;
    private final long workerIdShift;
    private final long dataCenterIdShift;
    private final long timestampLeftShift;

    private final long startTime;
    private final long dataCenterId;
    private final long workerId;

    @Builder
    public IdWorker(long sequenceMask, long workerIdShift, long dataCenterIdShift, long timestampLeftShift
            , long startTime, long dataCenterId, long workerId) {

        this.sequenceMask = sequenceMask;
        this.workerIdShift = workerIdShift;
        this.dataCenterIdShift = dataCenterIdShift;
        this.timestampLeftShift = timestampLeftShift;

        this.startTime = startTime;
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;

    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds"
                    , lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            //从 1-4095,再+1变成0
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        /*
            1. 时间差前移，空22个空位，空位为 0
            2. 数据中心id前移动17 （数据中心id 共计5位，即支持32个数据中心）
            3. 工作机器id前移动12位，（工作机器id 共计5位，即支持32个机器）
            4. 剩下的序列号 共计（2的12次方 即 4096个数据）
         */
        return ((timestamp - startTime) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }


}
