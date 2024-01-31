package com.venzee.ecpj.ECPJ.service.serviceImpl;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.AddToCartDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.CartDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.CartItemDto;
import com.venzee.ecpj.ECPJ.exception.CustomException;
import com.venzee.ecpj.ECPJ.model.Cart;
import com.venzee.ecpj.ECPJ.model.Product;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.repository.CartRepository;
import com.venzee.ecpj.ECPJ.repository.ProductRepository;
import com.venzee.ecpj.ECPJ.service.parents.ParentForCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ParentForCartService {
    private CartRepository cartRepository;
    private ProductService productService;
    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public  ApiResponse add(AddToCartDto addToCartDto, User user) {
     Product product=productService.findById(addToCartDto.getProductId());

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setMessage("Add Product To Cart Success.");
        apiResponse.setTimeStamp(new Date());


        return apiResponse;
    }

    @Override
    public CartDto list(User user) {
       List<Cart> cartList= cartRepository.findByUser(user);
       if (cartList==null){
           throw new CustomException("Cart not found by User.");
       }
       List<CartItemDto> cartItemDtos = new LinkedList<>();
       double totalCost =0;
       for (Cart cart:cartList){
           CartItemDto cartItemDto = new CartItemDto();
           cartItemDto.setProduct(cart.getProduct());
           cartItemDto.setQuantity(cart.getQuantity());
           cartItemDto.setId(cart.getId());
           cartItemDtos.add(cartItemDto);

           totalCost+= cart.getQuantity()*cart.getProduct().getPrice();
       }
       CartDto cartDto = new CartDto();
       cartDto.setCartItemDtos(cartItemDtos);
       cartDto.setTotalCost(totalCost);
       return cartDto;
    }

    @Override
    public void delete(Integer id, User user) {
    Optional<Cart> optionalCart =cartRepository.findById(id);
    if (optionalCart.isEmpty()){
        throw new CustomException("Cart Id: "+ id+" is valid.");
    }
    Cart cart = optionalCart.get();
    if (cart.getUser() != user){
        throw new CustomException("Cart isn't belong to this user: "+user);
    }
    cartRepository.delete(cart);
    }
}
