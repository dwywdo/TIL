package me.dwywdo.springvalidation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.dwywdo.springvalidation.validation.InputRequest;
import me.dwywdo.springvalidation.validation.ValidateController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ValidateController.class)
public class ValidateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenInputRequestIsInvalid_thenReturnStatus400() throws Exception {
        final InputRequest request = new InputRequest();
        // Wrong value for number 50 (It should be between 1 and 10)
        request.setNumberBetweenOneAndTen(50);

        // Right values
        request.setNotEmptyString("NotEmptyString");
        request.setPinCode("123456");

        final String body = objectMapper.writeValueAsString(request);

        System.out.println(body);

        mvc.perform(post("/validateRequestBody")
                            .contentType("application/json")
                            .content(body))
           .andExpect(status().isBadRequest());
    }
}
