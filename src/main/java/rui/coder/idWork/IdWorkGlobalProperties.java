package rui.coder.idWork;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("WeakerAccess")
@ConfigurationProperties("id-work.global")
@Getter
public class IdWorkGlobalProperties {

    /**
     * 序列码位长度 提供从0-127。每号码的长度
     */
    @Setter
    private long sequenceBits = 7;

    /**
     * 实例位长度 最大32 个实例
     */
    @Setter
    private long instanceBits = 5;

    /**
     * 项目位长度 最大支持1024个项目
     */
    @Setter
    private long projectBits = 10;

    /**
     * 机器ID向左移
     */
    private long workerIdShift = sequenceBits;
    /**
     * 数据标识id向左移
     */
    private long dataCenterIdShift = workerIdShift + instanceBits;
    /**
     * 时间截向左移
     */
    private long timestampLeftShift = dataCenterIdShift + projectBits;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) 2的12次方-1  -1 取符号位，然后
     */
    private long sequenceMask = ~(-1L << sequenceBits);
}
