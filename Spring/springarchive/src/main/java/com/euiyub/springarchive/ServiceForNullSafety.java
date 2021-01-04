package com.euiyub.springarchive;

import org.springframework.lang.NonNull;

public class ServiceForNullSafety {

    @NonNull
    public String createEvent(@NonNull String name) {
        return null;
    }
}
