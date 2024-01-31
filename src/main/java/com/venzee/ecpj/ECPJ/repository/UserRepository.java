package com.venzee.ecpj.ECPJ.repository;

import com.venzee.ecpj.ECPJ.dataTransferObject.user.SingUpDto;
import com.venzee.ecpj.ECPJ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String  email);
}
