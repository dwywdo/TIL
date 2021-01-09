package you.dwywdo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyTargetProperties.class)
public class MyTargetConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MyTarget myTarget(MyTargetProperties properties) {
        final MyTarget myTarget = new MyTarget();
        myTarget.setName(properties.getName());
        myTarget.setHowLong(properties.getHowLong());
        return myTarget;
    }
}
