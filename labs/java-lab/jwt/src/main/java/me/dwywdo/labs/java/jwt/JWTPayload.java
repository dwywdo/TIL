package me.dwywdo.labs.java.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTPayload {
    private final String email;
    private final String name;
    private final Boolean isAdmin;

    public JWTPayload(String email, String name, Boolean isAdmin) {
        this.email = email;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("isAdmin")
    public Boolean isAdmin() {
        return isAdmin;
    }
}
