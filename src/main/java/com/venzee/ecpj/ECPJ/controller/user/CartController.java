package com.venzee.ecpj.ECPJ.controller.user;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.AddToCartDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.CartDto;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.repository.UserRepository;
import com.venzee.ecpj.ECPJ.security.JWTService;
import com.venzee.ecpj.ECPJ.service.serviceImpl.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/user/cart")
@RequiredArgsConstructor
public class CartController {
    private final JWTService jwtService;
    private final CartService cartService;
    private final UserRepository userRepository;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(HttpServletRequest request, @RequestBody AddToCartDto addToCartDto){

      String token = jwtService.getJWTFromRequest(request);
      User user = jwtService.getUserFromToken(token);
      ApiResponse apiResponse= cartService.add(addToCartDto,user);
       return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<CartDto> list(HttpServletRequest request){
        String token = jwtService.getJWTFromRequest(request);
        User user = jwtService.getUserFromToken(token);
        CartDto cartDto = cartService.list(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);


    }
    @DeleteMapping("/{cartItemId}/delete")
    public ResponseEntity<ApiResponse> delete(@PathVariable("cartItemId")Integer id,
                                              HttpServletRequest request){
        String token = jwtService.getJWTFromRequest(request);
        User user = jwtService.getUserFromToken(token);

        cartService.delete(id,user);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Cart id: "+id+" deleted success.");
        apiResponse.setTimeStamp(new Date());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
