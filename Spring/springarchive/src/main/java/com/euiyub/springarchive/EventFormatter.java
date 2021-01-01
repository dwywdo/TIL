package com.euiyub.springarchive;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 * Also can be registered as a bean
 * which means this bean can use other injected beans
 * e.g. messageSource for utilizing Locale
 */
@Component
public class EventFormatter implements Formatter<Event> {

    // @Autowired
    // MessageSource messageSource;

    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        return new Event(Integer.parseInt(text));
    }

    @Override
    public String print(Event object, Locale locale) {
        // messageSource.getMessage("title", locale);
        return object.getId().toString();
    }
}
