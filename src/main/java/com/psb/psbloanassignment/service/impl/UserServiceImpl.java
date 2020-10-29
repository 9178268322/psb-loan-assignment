package com.psb.psbloanassignment.service.impl;

import com.psb.psbloanassignment.model.User;
import com.psb.psbloanassignment.repository.UserRepository;
import com.psb.psbloanassignment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        User localuser = userRepository.findByUsername(user.getUsername());

        if (localuser != null) {
            LOG.info("User {} already exists. Nothing will be done.", user.getUsername());
        } else {
            localuser = userRepository.save(user);
        }
        return localuser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
