package rui.coder.idWork;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@ConfigurationProperties("id-work")
public class IdWorkProperties {

    /**
     * 开始时间截
     */
    private long startTime;


    /**
     * 实例id
     */
    private long instanceId;
    /**
     * 项目id
     */
    private long projectId;

    private IdWorkGlobalProperties global=new IdWorkGlobalProperties();

    @PostConstruct
    void check() {
        /* 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) 即1f */
        long maxWorkerId = ~(-1L << global.getInstanceBits());
        /* 支持的最大数据标识id，结果是31 即0x1f */
        long maxDataCenterId = ~(-1L << global.getProjectBits());


        if (projectId > maxDataCenterId || projectId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0"
                    , maxDataCenterId));
        }

        if (instanceId > maxWorkerId || instanceId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0"
                    , maxWorkerId));
        }
    }
}
