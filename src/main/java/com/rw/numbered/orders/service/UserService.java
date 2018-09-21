package com.rw.numbered.orders.service;

import com.rw.numbered.orders.security.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User createUser(String email, String phone) {
        return new User();
    }
}
