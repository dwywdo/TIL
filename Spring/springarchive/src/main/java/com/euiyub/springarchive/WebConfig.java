package com.euiyub.springarchive;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.euiyub.springarchive.EventConverter.EventToStringConverter;
import com.euiyub.springarchive.EventConverter.StringToEventConverter;

/**
 * Java web config for Spring MVC without Spring Boot
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEventConverter());
        registry.addConverter(new EventToStringConverter());
    }
}
