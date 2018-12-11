package rui.coder.idWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {IdWorkProperties.class})
public class IdWorkConfiguration {

    @Autowired
    private IdWorkProperties properties;


    @Bean
    public IdWorker idWorker() {
        IdWorkGlobalProperties global = properties.getGlobal();
        return IdWorker.builder()
                .sequenceMask(global.getSequenceMask())
                .workerIdShift(global.getWorkerIdShift())
                .dataCenterIdShift(global.getDataCenterIdShift())
                .timestampLeftShift(global.getTimestampLeftShift())
                .startTime(properties.getStartTime())
                .dataCenterId(properties.getProjectId())
                .workerId(properties.getInstanceId())
                .build();
    }


}
