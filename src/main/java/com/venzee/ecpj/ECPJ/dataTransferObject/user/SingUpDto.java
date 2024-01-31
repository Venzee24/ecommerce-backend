package com.venzee.ecpj.ECPJ.dataTransferObject.user;

import lombok.Data;

@Data
public class SingUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
