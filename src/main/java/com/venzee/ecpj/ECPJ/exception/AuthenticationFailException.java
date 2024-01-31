package com.venzee.ecpj.ECPJ.exception;

import java.security.NoSuchAlgorithmException;

public class AuthenticationFailException extends RuntimeException {
    public AuthenticationFailException(String message){
        super(message);
    }
}
