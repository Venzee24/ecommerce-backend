package com.venzee.ecpj.ECPJ.controller.user;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.model.WishList;
import com.venzee.ecpj.ECPJ.repository.UserRepository;
import com.venzee.ecpj.ECPJ.security.JWTService;
import com.venzee.ecpj.ECPJ.service.serviceImpl.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user/wishList")
public class WishListController {

    private JWTService tokenService;
    private UserRepository userRepository;
    private WishListService wishListService;

    @Autowired
    public WishListController(JWTService tokenService, WishListService wishListService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.wishListService=wishListService;
        this.userRepository=userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody Product product,
                                           HttpServletRequest request){

        String token = tokenService.getJWTFromRequest(request);
        User user = tokenService.getUserFromToken(token);

        WishList wishList = new WishList();
        wishList.setUser(user);
        wishList.setProduct(product);
        wishList.setCreatedDate(new Date());

       ApiResponse result= wishListService.add(wishList);
     return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductDto>> getWishList(HttpServletRequest request){
        String token = tokenService.getJWTFromRequest(request);
        User user = tokenService.getUserFromToken(token);

        List<ProductDto> productDtos=wishListService.getWishListByUser(user);

        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> delete(HttpServletRequest request,
                                              @PathVariable("id")int id
                                              ){
        String token = tokenService.getJWTFromRequest(request);
        User user = tokenService.getUserFromToken(token);
        wishListService.delete(user,id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("WishList Id : "+id+" deleted success.");
        apiResponse.setTimeStamp(new Date());

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
