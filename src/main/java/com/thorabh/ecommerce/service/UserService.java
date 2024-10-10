package com.thorabh.ecommerce.service;

import com.thorabh.ecommerce.utils.CustomUserDetails;
import com.thorabh.ecommerce.entity.User;
import com.thorabh.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(getRoles(user))
//                .build();

        return new CustomUserDetails(user);






    }

//    private String[] getRoles(User user) {
//        if (user.getRole() == null) {
//            return new String[]{"user"};
//        }
//        return user.getRole().split(",");
//    }

    public User createUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}

