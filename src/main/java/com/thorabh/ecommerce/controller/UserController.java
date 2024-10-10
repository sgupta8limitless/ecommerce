package com.thorabh.ecommerce.controller;

import com.thorabh.ecommerce.beans.GlobalResponseHandler;
import com.thorabh.ecommerce.utils.CustomUserDetails;
import com.thorabh.ecommerce.entity.User;
import com.thorabh.ecommerce.service.UserService;
import com.thorabh.ecommerce.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;



    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody User user)
    {
        return GlobalResponseHandler.createResponse("User Created",userService.createUser(user), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User authRequest)
    {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
        );

        if(authentication.isAuthenticated())
        {

            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map((authority)-> authority.getAuthority())
                    .collect(Collectors.toList());

            CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();


            HashMap<String,Object> userDetails = new HashMap<>();

            userDetails.put("token",jwtService.generateToken(authRequest.getUsername()));
            userDetails.put("roles",roles);
            userDetails.put("username",customUserDetails.getUsername());
//


            return GlobalResponseHandler.createResponse("Login SuccessFull",userDetails,HttpStatus.OK);
        }
        else
        {
            return GlobalResponseHandler.createResponse("Wrong Username or Password",HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/home")
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "Welcome to ADMIN home!";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "Welcome to USER home!";
    }


}
