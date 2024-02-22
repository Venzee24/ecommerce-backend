package com.venzee.ecpj.ECPJ.service.serviceImpl;

import com.venzee.ecpj.ECPJ.exception.CategoryNotFoundException;
import com.venzee.ecpj.ECPJ.model.Category;
import com.venzee.ecpj.ECPJ.repository.CategoryRepo;
import com.venzee.ecpj.ECPJ.service.parents.ParentForCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ParentForCategoryService {


    private CategoryRepo categoryRepo;
    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public String create(Category category) {
    categoryRepo.save(category);
    return "Success";
    }

    @Override
    public List<Category> listCategory() {
       return categoryRepo.findAll();
    }

    @Override
    public Category update(int id, Category category) {
       Category categoryResult= categoryRepo.findById(id).orElseThrow(()->new CategoryNotFoundException("Category not found"));
       categoryResult.setCategoryName(category.getCategoryName());
       categoryResult.setDescription(category.getDescription());
       categoryResult.setImageUrl(category.getImageUrl());

       return categoryRepo.save(categoryResult);


    }


}
