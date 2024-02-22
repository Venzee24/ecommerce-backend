package com.venzee.ecpj.ECPJ.service.parents;

import com.venzee.ecpj.ECPJ.common.ApiResponse;
import com.venzee.ecpj.ECPJ.dataTransferObject.ProductDto;
import com.venzee.ecpj.ECPJ.model.User;
import com.venzee.ecpj.ECPJ.model.WishList;

import java.util.List;

public interface ParentForWishListService {
    ApiResponse add(WishList wishList);

    List<ProductDto> getWishListByUser(User user);

    void delete(User user, int id);
}
