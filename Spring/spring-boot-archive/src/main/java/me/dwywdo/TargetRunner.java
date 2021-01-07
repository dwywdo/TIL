package me.dwywdo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import you.dwywdo.MyTarget;

@Component
public class TargetRunner implements ApplicationRunner {

    @Autowired
    MyTarget myTarget;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(myTarget);
    }
}
