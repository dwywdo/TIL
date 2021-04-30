package me.dwywdo.springvalidation.validation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateController {
    @PostMapping("/validateRequestBody")
    ResponseEntity<String> validateRequestBody(@Valid @RequestBody InputRequest request) {
        return ResponseEntity.ok("Valid");
    }

    @PostMapping("/validateResponseBody")
    OutputResponse validateResponseBody(@RequestBody InputRequest request) {
        OutputResponse response = new OutputResponse(request.getNumberBetweenOneAndTen(),
                                                     null,
                                                     request.getPinCode());
        return response;
    }
}
