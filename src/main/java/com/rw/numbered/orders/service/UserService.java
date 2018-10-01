package com.rw.numbered.orders.service;

import com.rw.numbered.orders.security.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String registerUser(String email, String phone) {
        String jwt = "test";
        return jwt;
    }
}
