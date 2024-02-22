package com.venzee.ecpj.ECPJ.controller.user;

import com.venzee.ecpj.ECPJ.model.Category;
import com.venzee.ecpj.ECPJ.service.serviceImpl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/category")
@RequiredArgsConstructor
public class CategoryControllerForUser {
    private final CategoryService categoryService;
    @GetMapping("/list")
    public ResponseEntity<List<Category>> listCategory(){
        return new ResponseEntity<>(categoryService.listCategory(), HttpStatus.OK);
    }
}
