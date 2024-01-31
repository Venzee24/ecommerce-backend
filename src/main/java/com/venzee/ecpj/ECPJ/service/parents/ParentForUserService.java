package com.venzee.ecpj.ECPJ.service.parents;

import com.venzee.ecpj.ECPJ.dataTransferObject.user.ResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SingUpDto;

public interface ParentForUserService {
    ResponseDto signUp(SingUpDto singUpDto);
    SignInResponseDto signIn(SignInDto signInDto);
}
