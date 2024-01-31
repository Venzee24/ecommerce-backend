package com.venzee.ecpj.ECPJ.exception;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCategoryNotFound(CategoryNotFoundException ex, WebRequest request){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setTimeStamp(new Date());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse> handleProductNotFound(ProductNotFoundException ex,WebRequest request){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setTimeStamp(new Date());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDto> handleUserException(UserAlreadyExistsException ex, WebRequest request){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.ALREADY_REPORTED.value());
        responseDto.setMessage(ex.getMessage());
        responseDto.setTimeStamp(new Date());
        return new ResponseEntity<>(responseDto,HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto> handleUserException(UserNotFoundException ex, WebRequest request){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.NOT_FOUND.value());
        responseDto.setMessage(ex.getMessage());
        responseDto.setTimeStamp(new Date());
        return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity<ResponseDto> handleAuthenticationFail(AuthenticationFailException exception){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.BAD_REQUEST.value());
        responseDto.setMessage(exception.getMessage());
        responseDto.setTimeStamp(new Date());
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto> handleCustomException(CustomException ex){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.NOT_FOUND.value());
        responseDto.setMessage(ex.getMessage());
        responseDto.setTimeStamp(new Date());
        return new ResponseEntity<>(responseDto,HttpStatus.NOT_FOUND);
    }

}
