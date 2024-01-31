package com.venzee.ecpj.ECPJ.controller;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.model.WishList;
import com.venzee.ecpj.ECPJ.service.serviceImpl.TokenService;
import com.venzee.ecpj.ECPJ.service.serviceImpl.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/wishList")
public class WishListController {

    private TokenService tokenService;
    private WishListService wishListService;

    @Autowired
    public WishListController(TokenService tokenService,WishListService wishListService) {
        this.tokenService = tokenService;
        this.wishListService=wishListService;
    }

    @PostMapping("/add/{token}")
    public ResponseEntity<ApiResponse> add(@RequestBody Product product,
                                           @PathVariable("token")String token){
        tokenService.authenticate(token);

        User user = tokenService.getUser(token);

        WishList wishList = new WishList();
        wishList.setUser(user);
        wishList.setProduct(product);
        wishList.setCreatedDate(new Date());

       ApiResponse result= wishListService.add(wishList);
     return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{token}/get")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token")String token){
        tokenService.authenticate(token);

        User user = tokenService.getUser(token);

        List<ProductDto> productDtos=wishListService.getWishListByUser(user);

        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

}
