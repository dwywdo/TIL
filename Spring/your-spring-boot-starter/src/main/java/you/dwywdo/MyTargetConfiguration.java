package you.dwywdo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyTargetConfiguration {

    @Bean
    public MyTarget target() {
        final MyTarget myTarget = new MyTarget();
        myTarget.setName("I'm an auto-configured target");
        myTarget.setHowLong(10);
        return myTarget;
    }
}
