package com.psb.psbloanassignment.service;

import com.psb.psbloanassignment.model.User;

public interface UserService {

    User findByUsername(String username);
    User findByEmail(String email);
    User createUser(User user);
    User save(User user);
}
