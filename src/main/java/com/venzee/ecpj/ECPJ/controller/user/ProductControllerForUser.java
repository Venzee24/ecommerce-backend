package com.venzee.ecpj.ECPJ.controller.user;

import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.service.serviceImpl.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/product")
@RequiredArgsConstructor
public class ProductControllerForUser {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> list(){
        return new ResponseEntity<>(productService.list(), HttpStatus.OK);
    }

}
