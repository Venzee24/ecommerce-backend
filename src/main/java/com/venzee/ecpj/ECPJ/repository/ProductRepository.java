package com.venzee.ecpj.ECPJ.repository;

import com.venzee.ecpj.ECPJ.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
