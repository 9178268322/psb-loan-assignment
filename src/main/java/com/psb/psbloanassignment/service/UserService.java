package com.psb.psbloanassignment.service;

import com.psb.psbloanassignment.model.User;
import com.psb.psbloanassignment.model.security.UserRole;

import java.util.Set;

public interface UserService {

    User findByUsername(String username);
    User findByEmail(String email);
    User createUser(User user, Set<UserRole> userRoles) throws Exception;
    User save(User user);
}
