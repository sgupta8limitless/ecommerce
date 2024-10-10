package com.thorabh.ecommerce.beans;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class GlobalResponseHandler {

    public static ResponseEntity<Object> createResponse(String message, Object payload, HttpStatus httpStatus)
    {

        HashMap<String,Object> response = new HashMap<>();

        response.put("message",message);
        response.put("data",payload);

        return new ResponseEntity<>(response, httpStatus);


    }

    public static ResponseEntity<Object> createResponse(String message, HttpStatus httpStatus)
    {

        HashMap<String,Object> response = new HashMap<>();

        response.put("message",message);
        return new ResponseEntity<>(response, httpStatus);


    }

}
