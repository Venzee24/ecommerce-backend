package com.venzee.ecpj.ECPJ.service.parents;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.model.Category;
import com.venzee.ecpj.ECPJ.model.Product;

import java.util.List;

public interface ParentForProductService {
    String add(ProductDto productDto) ;
    List<ProductDto> list();
    ProductDto update(long id,ProductDto productDto);

    Product findById(Integer productId);
}
