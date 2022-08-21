package avia.cloud.gateway.auth.util;

import avia.cloud.gateway.auth.exception.JwtKeyException;
import avia.cloud.gateway.auth.record.SignupRecord;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import static lombok.AccessLevel.PRIVATE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class JwtProvider {

    KeyStore keyStore;
    final PasswordEncoder passwordEncoder;
    final BCryptPasswordEncoder encoder;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/keystore/avia.jks");
            keyStore.load(resourceAsStream, "usblaster".toCharArray());
        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new JwtKeyException("Exception occurred while loading keystore");
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("avia", "usblaster".toCharArray());
        } catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
            throw new JwtKeyException("Exception occurred while loading keystore");
        }
    }

    public boolean validateToken(String jwt) {
        Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("avia").getPublicKey();
        } catch (KeyStoreException e) {
            throw new JwtKeyException("Exception occurred while loading keystore");
        }
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean validatePassword(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
