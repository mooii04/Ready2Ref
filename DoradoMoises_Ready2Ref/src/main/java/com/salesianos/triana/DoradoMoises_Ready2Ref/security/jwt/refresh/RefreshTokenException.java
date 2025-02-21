package com.salesianos.triana.DoradoMoises_Ready2Ref.security.jwt.refresh;

import com.salesianos.triana.DoradoMoises_Ready2Ref.security.exceptionhandling.JwtException;

public class RefreshTokenException extends JwtException {
    public RefreshTokenException(String s) {
        super(s);
    }
}
