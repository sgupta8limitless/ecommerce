package com.thorabh.ecommerce.controller;

import com.thorabh.ecommerce.beans.GlobalResponseHandler;
import com.thorabh.ecommerce.entity.CartItem;
import com.thorabh.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/cartitems")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @PostMapping("")
    public ResponseEntity<Object> addToCart(@RequestBody CartItem cartItem)
    {

        return GlobalResponseHandler.createResponse(
                "Added To Cart",
                cartItemService.create(cartItem),
                HttpStatus.CREATED

        );

    }


    @GetMapping("/user")
    public ResponseEntity<Object> fetchByUser()
    {
        return GlobalResponseHandler.createResponse(
                "Cart Products",
                cartItemService.findByUserId(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@RequestBody CartItem cartItem,@PathVariable Long id)
    {
        return GlobalResponseHandler.createResponse(
                "Quantity Updated",
                cartItemService.update(cartItem,id),
                HttpStatus.OK
        );
    }

}
