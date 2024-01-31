package com.venzee.ecpj.ECPJ.service.serviceImpl;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.exception.AuthenticationFailException;
import com.venzee.ecpj.ECPJ.exception.UserNotFoundException;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.model.WishList;
import com.venzee.ecpj.ECPJ.repository.WishListRepository;
import com.venzee.ecpj.ECPJ.service.parents.ParentForWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class WishListService implements ParentForWishListService {
    private WishListRepository wishListRepository;

    private ProductService productService;
    @Autowired
    public WishListService(WishListRepository wishListRepository,ProductService productService) {
        this.wishListRepository = wishListRepository;
        this.productService=productService;
    }
    @Override
    public ApiResponse add(WishList wishList){
        if (wishList==null){
            throw new AuthenticationFailException("WishList not found");
        }
        wishListRepository.save(wishList);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("WishList added success.");
        apiResponse.setTimeStamp(new Date());
        return apiResponse;
    }

    @Override
    public List<ProductDto> getWishListByUser(User user) {
       List<WishList> wishLists= wishListRepository.findByUser(user);
       if (wishLists.isEmpty()){
           throw new UserNotFoundException("WishList not found by user.");
       }
       List<ProductDto> productDtos = new LinkedList<>();
       for (WishList wishList: wishLists){
           productDtos.add(productService.mapToProductDto(wishList.getProduct()));
       }
       return productDtos;
    }
}
