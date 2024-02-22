package com.venzee.ecpj.ECPJ.service.parents;

import com.venzee.ecpj.ECPJ.dataTransferObject.user.ResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignUpDto;

public interface ParentForUserService {
    ResponseDto signUp(SignUpDto signUpDto);
    SignInResponseDto signIn(SignInDto signInDto);
}
