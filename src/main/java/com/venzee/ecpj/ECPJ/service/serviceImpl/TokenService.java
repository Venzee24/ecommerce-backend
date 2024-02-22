package com.venzee.ecpj.ECPJ.service.serviceImpl;

import com.venzee.ecpj.ECPJ.exception.AuthenticationFailException;
import com.venzee.ecpj.ECPJ.exception.UserNotFoundException;
import com.venzee.ecpj.ECPJ.model.AuthenticationToken;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.repository.TokenRepository;
import com.venzee.ecpj.ECPJ.service.parents.ParentForTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ParentForTokenService {
    private TokenRepository tokenRepository;
    @Autowired
    public TokenService(TokenRepository tokenRepository){
        this.tokenRepository=tokenRepository;
    }
    public void saveComfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token){
        AuthenticationToken token1 = tokenRepository.findByToken(token);
        if (token1==null){
            return null;

        }
        User user = token1.getUser();
        return user;
    }
    public void authenticate(String token){
        if (token==null){
            throw new AuthenticationFailException("Token isn't exists.");
        }
        if (getUser(token)==null){
            throw new UserNotFoundException("User not found by Token.");
        }


    }
}
