package com.venzee.ecpj.ECPJ.service.parents;

import com.venzee.ecpj.ECPJ.model.Category;

import java.util.List;

public interface ParentForCategoryService {
    String create(Category category);
    List<Category> listCategory();

    Category update(int id,Category category);
}
