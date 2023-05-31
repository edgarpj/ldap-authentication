package com.example.ldap.controllers;

import com.example.ldap.entities.User;
import com.example.ldap.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @GetMapping("/users/current")
    public ResponseEntity getCurrentUser(final Principal principal) {
        final UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) principal;
        final LdapUserDetailsImpl userDetails = (LdapUserDetailsImpl) authToken.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        final List<String> users = userService.fetchAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(final String fullName, final String userName, final String password, final String role) {
        final User user = userService.createUser(fullName, userName, password, role);
        return ResponseEntity.ok(Collections.singletonMap("username", user.getUid()));
    }
}
