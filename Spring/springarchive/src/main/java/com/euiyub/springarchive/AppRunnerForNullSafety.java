package com.euiyub.springarchive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class AppRunnerForNullSafety implements ApplicationRunner {

    @Autowired
    ServiceForNullSafety serviceForNullSafety;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        serviceForNullSafety.createEvent(null);
    }
}
