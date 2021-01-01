package com.euiyub.springarchive;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * EventFormatter.class or EventConverter.StringToEventConverter
 * should be registered as bean(using @Component, etc)
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest({EventFormatter.class, EventController.class}) // It's ok to omit
// @WebMvcTest({EventConverter.StringToEventConverter.class, EventController.class})
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/event/1")) // 1 should be converted to Event type
               .andExpect(status().isOk())
               .andExpect(content().string("1"));
    }

}
