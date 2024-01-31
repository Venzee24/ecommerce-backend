package com.venzee.ecpj.ECPJ.service.serviceImpl;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.exception.CategoryNotFoundException;
import com.venzee.ecpj.ECPJ.exception.ProductNotFoundException;
import com.venzee.ecpj.ECPJ.model.Category;
import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.repository.CategoryRepo;
import com.venzee.ecpj.ECPJ.repository.ProductRepository;
import com.venzee.ecpj.ECPJ.service.parents.ParentForProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ParentForProductService {
    private ProductRepository productRepository;
    private CategoryRepo categoryRepo;
    @Autowired
    public ProductService(ProductRepository productRepository,CategoryRepo categoryRepo) {
        this.productRepository = productRepository;
        this.categoryRepo=categoryRepo;

    }

    @Override
    public String add(ProductDto productDto) {

       Product product=mapToProduct(productDto);
       productRepository.save(product);

       return "Category added success.";

    }
    public ProductDto mapToProductDto(Product product){
        ProductDto productDto = new ProductDto();
       productDto.setId(product.getId());
       productDto.setName(product.getName());
       productDto.setPrice(product.getPrice());
       productDto.setImageUrl(product.getImageUrl());
       productDto.setDescription(product.getDescription());
       productDto.setCategoryId(product.getCategory().getId());

        return productDto;
    }
    public Product mapToProduct(ProductDto productDto)  {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        Category category =categoryRepo.findById(productDto.getCategoryId()).orElseThrow(()->new CategoryNotFoundException("Category Not found."));
        product.setCategory(category);
        return product;
    }

    @Override
    public List<ProductDto> list() {
      List<Product> list=  productRepository.findAll();

      return list.stream().map(e->mapToProductDto(e)).collect(Collectors.toList());
    }

    @Override
    public ProductDto update(long id,ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow(()->new CategoryNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return mapToProductDto(product);

    }

    @Override
    public Product findById(Integer productId) {
    Optional<Product> product = productRepository.findById((productId.longValue()));
    if (product.isEmpty()){
        throw new ProductNotFoundException("Product not found by Id");
    }
    return product.get();
    }

}
