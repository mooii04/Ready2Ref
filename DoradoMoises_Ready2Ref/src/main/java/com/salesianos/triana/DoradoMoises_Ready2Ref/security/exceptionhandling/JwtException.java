package com.salesianos.triana.DoradoMoises_Ready2Ref.security.exceptionhandling;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
