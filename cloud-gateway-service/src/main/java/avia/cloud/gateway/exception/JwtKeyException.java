package avia.cloud.gateway.exception;

import javax.naming.AuthenticationException;

public class JwtKeyException extends RuntimeException {

    public JwtKeyException(String message) {
        super(message);
    }
}
