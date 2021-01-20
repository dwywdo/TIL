package me.dwywdo.springboottest.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
class SampleControllerTest3 {

    @Autowired
    JacksonTester<Sample> sampleJacksonTester;

    @Test
    public void hello() throws Exception {

    }
}
