package com.venzee.ecpj.ECPJ.controller;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.model.Category;
import com.venzee.ecpj.ECPJ.service.serviceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Category category){
       String result= categoryService.create(category);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listCategory(){
        return new ResponseEntity<>(categoryService.listCategory(),HttpStatus.OK);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Category> editCategory(@PathVariable("id") int categoryId, @RequestBody Category category){

        Category result=categoryService.update(categoryId,category);
       return new ResponseEntity<>(result,HttpStatus.OK);
    }



}
