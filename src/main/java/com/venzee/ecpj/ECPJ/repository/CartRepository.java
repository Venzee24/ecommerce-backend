package com.venzee.ecpj.ECPJ.repository;

import com.venzee.ecpj.ECPJ.dataTransferObject.CartItemDto;
import com.venzee.ecpj.ECPJ.model.Cart;
import com.venzee.ecpj.ECPJ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByUser(User user);
    Cart findById(int id);
}
