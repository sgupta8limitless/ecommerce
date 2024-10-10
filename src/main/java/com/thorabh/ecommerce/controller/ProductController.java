package com.thorabh.ecommerce.controller;

import com.thorabh.ecommerce.beans.GlobalResponseHandler;
import com.thorabh.ecommerce.entity.Product;
import com.thorabh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> createProduct(@RequestBody Product product)
    {
        return GlobalResponseHandler.createResponse("Product Created",productService.create(product), HttpStatus.CREATED);
    }

    @GetMapping("/index")
    public ResponseEntity<Object> fetchAll()
    {
        return GlobalResponseHandler.createResponse("All Fetched",productService.index(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id)
    {
        return GlobalResponseHandler.createResponse(productService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String doSomething()
    {
        return "this is admin";
    }


}
