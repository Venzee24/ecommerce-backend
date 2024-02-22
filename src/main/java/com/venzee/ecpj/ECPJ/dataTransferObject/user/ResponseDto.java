package com.venzee.ecpj.ECPJ.dataTransferObject.user;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseDto {
    private int status;
    private String message;
    private Date timeStamp;
}
