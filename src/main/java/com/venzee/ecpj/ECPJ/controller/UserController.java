package com.venzee.ecpj.ECPJ.controller;

import com.venzee.ecpj.ECPJ.dataTransferObject.user.ResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SingUpDto;
import com.venzee.ecpj.ECPJ.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signUp")
    public ResponseEntity<ResponseDto> signUp(@RequestBody SingUpDto singUpDto){
      ResponseDto responseDto=  userService.signUp(singUpDto);
      return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInDto signInDto){
        SignInResponseDto responseDto = userService.signIn(signInDto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }


}
