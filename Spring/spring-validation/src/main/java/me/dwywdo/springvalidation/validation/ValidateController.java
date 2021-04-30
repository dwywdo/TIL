package me.dwywdo.springvalidation.validation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateRequestBodyController {
    @PostMapping("/validateBody")
    OutputResponse validateBody(@Valid @RequestBody InputRequest request) {
        OutputResponse response = new OutputResponse();

        response.setNumberBetweenOneAndTen(request.getNumberBetweenOneAndTen());
        response.setNotEmptyString(request.getNotEmptyString());
        response.setPinCode(request.getPinCode());

        return response;
    }
}
