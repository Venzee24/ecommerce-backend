package com.venzee.ecpj.ECPJ.repository;

import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {
  List<WishList> findByUser(User user);
}
