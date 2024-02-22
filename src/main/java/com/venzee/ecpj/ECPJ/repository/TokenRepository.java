package com.venzee.ecpj.ECPJ.repository;

import com.venzee.ecpj.ECPJ.model.AuthenticationToken;
import com.venzee.ecpj.ECPJ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
