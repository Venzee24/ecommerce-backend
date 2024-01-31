package com.venzee.ecpj.ECPJ.controller;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.AddToCartDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.CartDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.CartItemDto;
import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.service.serviceImpl.CartService;
import com.venzee.ecpj.ECPJ.service.serviceImpl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private TokenService tokenService;
    private CartService cartService;

    @Autowired
    public CartController(TokenService tokenService, CartService cartService) {
        this.tokenService = tokenService;
        this.cartService = cartService;
    }
    @PostMapping("/add/{token}")
    public ResponseEntity<ApiResponse> add(@PathVariable("token")String token, @RequestBody AddToCartDto addToCartDto){
        tokenService.authenticate(token);

        User user = tokenService.getUser(token);

       ApiResponse apiResponse= cartService.add(addToCartDto,user);
       return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/list/{token}")
    public ResponseEntity<CartDto> list(@PathVariable("token")String token){
        tokenService.authenticate(token);

        User user = tokenService.getUser(token);

        CartDto cartDto = cartService.list(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);


    }
    @DeleteMapping("/{cartItemId}/delete/{token}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("cartItemId")Integer id,
                                              @PathVariable("token")String token){
        tokenService.authenticate(token);
        User user = tokenService.getUser(token);

        cartService.delete(id,user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Cart id: "+id+" deleted success.");
        apiResponse.setTimeStamp(new Date());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
