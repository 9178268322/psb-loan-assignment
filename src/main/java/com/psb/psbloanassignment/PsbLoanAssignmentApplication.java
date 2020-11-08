package com.psb.psbloanassignment;

import com.psb.psbloanassignment.model.User;
import com.psb.psbloanassignment.model.security.Role;
import com.psb.psbloanassignment.model.security.UserRole;
import com.psb.psbloanassignment.service.UserService;
import com.psb.psbloanassignment.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PsbLoanAssignmentApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PsbLoanAssignmentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("dipu");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
        user1.setEmail("admin@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(0);
        role1.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);
    }
}
