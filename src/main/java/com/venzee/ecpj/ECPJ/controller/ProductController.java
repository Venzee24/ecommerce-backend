package com.venzee.ecpj.ECPJ.controller;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.model.Category;
import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.repository.CategoryRepo;
import com.venzee.ecpj.ECPJ.service.serviceImpl.CategoryService;
import com.venzee.ecpj.ECPJ.service.serviceImpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody ProductDto productDto) {

        String  result= productService.add(productDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> list(){
        return new ResponseEntity<>(productService.list(),HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProductDto> update(@PathVariable("id") int id,@RequestBody ProductDto productDto){
       ProductDto result= productService.update(id,productDto);
       return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
