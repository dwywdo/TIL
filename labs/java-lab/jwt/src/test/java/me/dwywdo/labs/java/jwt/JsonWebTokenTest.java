package me.dwywdo.labs.java.jwt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonWebTokenTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SECRET_KEY = "secret_key";

    @Test
    void createJWT() throws Exception {
        final JWTHeader header = new JWTHeader("HS256", "JWT");
        final JWTPayload payload = new JWTPayload("dwywdo@gmail.com", "Euiyub_Jung", true);

        final String base64Header = getBase64EncodedStringWithoutPadding(header);
        final String base64Payload = getBase64EncodedStringWithoutPadding(payload);

        System.out.println("base64Header = " + base64Header);
        System.out.println("base64Payload = " + base64Payload);

        final String base64Data = base64Header + '.' + base64Payload;
        System.out.println("base64Data = " + base64Data);

        final String Base64HmacSHA256 = createHMAC("HmacSHA256", base64Data, SECRET_KEY);
        System.out.println("base64HmacSHA256 = " + Base64HmacSHA256);

        final String jsonWebToken = base64Header + '.' + base64Payload + '.' + Base64HmacSHA256;
        System.out.println("jsonWebToken = " + jsonWebToken);
    }

    private String getBase64EncodedStringWithoutPadding(Object object) throws JsonProcessingException {
        // We MUST remove padding because it's NOT URL Safe. It's always possible to decode it regardless of paddings
        final String jsonAsString = objectMapper.writeValueAsString(object);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(jsonAsString.getBytes());
    }

    private static String createHMAC(String algorithm, String data, String secret)
            throws NoSuchAlgorithmException, InvalidKeyException {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), algorithm);
        final Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKeySpec);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(data.getBytes()));
    }
}
