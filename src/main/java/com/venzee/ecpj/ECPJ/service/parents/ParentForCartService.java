package com.venzee.ecpj.ECPJ.service.parents;

import com.venzee.ecpj.ECPJ.dataTransferObject.AddToCartDto;
import com.venzee.ecpj.ECPJ.dataTransferObject.CartDto;
import com.venzee.ecpj.ECPJ.model.User;

public interface ParentForCartService {
    CartDto list(User user);

    void delete(Integer id, User user);


}
