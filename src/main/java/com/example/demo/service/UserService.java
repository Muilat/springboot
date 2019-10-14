package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

public interface UserService  {
    ResponseEntity<User> add(User user) throws URISyntaxException;
    User getById(Long id);
    User update(User user);
    List<User> getAllUsers();
    Boolean delete(Long id);
}
