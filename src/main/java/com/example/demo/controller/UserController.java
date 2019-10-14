package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value ="/users")
@Validated
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/add")
    public ResponseEntity<User> save(@Valid @RequestBody User user) throws URISyntaxException {
        return userService.add(user);
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable
                    @Min(value = 1, message = "id must be greater than or equal to 1")
                    @Max(value = 1000, message = "id must be lower than or equal to 1000")
                            Long id){
        return userService.getById(id);

    }

    @GetMapping(value = "/")
    public List<User> getAll(){
//        System.out.println("Hereeeeeeeeeeeeeeeeeeeee");

        return userService.getAllUsers();
    }
}
