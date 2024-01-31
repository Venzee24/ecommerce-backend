package com.venzee.ecpj.ECPJ.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ApiResponse {
    private  int status;
    private  String message;
    private  Date timeStamp;
}
