package com.venzee.ecpj.ECPJ.dataTransferObject.user;

import lombok.Data;

@Data
public class SignInResponseDto {
    private String token;
    private String refreshedToken;
}
