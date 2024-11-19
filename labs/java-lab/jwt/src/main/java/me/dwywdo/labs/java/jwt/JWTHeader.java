package me.dwywdo.labs.java.jwt;

public class JWTHeader {
    private final String alg;
    private final String typ;

    public JWTHeader(String alg, String typ) {
        this.alg = alg;
        this.typ = typ;
    }

    public String getAlg() {
        return alg;
    }

    public String getTyp() {
        return typ;
    }
}
