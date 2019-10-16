package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface UserService  {
    ResponseEntity<User> add(User user) throws URISyntaxException;

    User getById(Long id);

    User getByUsername(String username);

    User update(User user);
    List<User> getAllUsers();
    Boolean delete(Long id);
}
