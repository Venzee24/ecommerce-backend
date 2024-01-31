package com.venzee.ecpj.ECPJ.service.serviceImpl;

import com.venzee.ecpj.ECPJ.dataTransferObject.user.ResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SignInResponseDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.user.SingUpDto;
import com.venzee.ecpj.ECPJ.exception.AuthenticationFailException;
import com.venzee.ecpj.ECPJ.exception.UserAlreadyExistsException;
import com.venzee.ecpj.ECPJ.exception.UserNotFoundException;
import com.venzee.ecpj.ECPJ.model.AuthenticationToken;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.repository.UserRepository;
import com.venzee.ecpj.ECPJ.service.parents.ParentForUserService;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements ParentForUserService {
    private UserRepository userRepository;
    private TokenService tokenService;
    @Autowired
    public UserService(UserRepository userRepository,TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService=tokenService;
    }

    @Override
    @Transactional
    public ResponseDto signUp(SingUpDto singUpDto) {
        User testUser =userRepository.findByEmail(singUpDto.getEmail());
        if (testUser != null){
            throw new UserAlreadyExistsException("User already exists.");
        }
        //hashPassword
        String encryptedPassword = singUpDto.getPassword();

        try{
             encryptedPassword=hashPassword(singUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new UserAlreadyExistsException(e.getMessage());
        }
        User user = new User();
        user.setEmail(singUpDto.getEmail());
        user.setPassword(encryptedPassword );
        user.setFirst_name(singUpDto.getFirstName());
        user.setLast_name(singUpDto.getLastName());

        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setUser(user);
        authenticationToken.setToken(UUID.randomUUID().toString());
        authenticationToken.setCreatedDate(new Date());

        tokenService.saveComfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.CREATED.value());
        responseDto.setMessage("User Sign Up Success.");
        responseDto.setTimeStamp(new Date());
        return responseDto;

    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (user==null){
            throw new UserNotFoundException("User is not valid.");
        }
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("Password incorrect.");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        AuthenticationToken token = tokenService.getToken(user);
        if (token==null){
            throw new AuthenticationFailException("Token isn't exists.");
        }
        SignInResponseDto signInResponseDto = new SignInResponseDto();
        signInResponseDto.setStatus(HttpStatus.OK.value());
        signInResponseDto.setToken(token.getToken());
        return signInResponseDto;

    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;

    }
}
