package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AccessToken;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value ="/users")
@Validated
public class UserController {

    @Autowired
    UserServiceImpl userService;

    RestTemplate restTemplate;
//
//    public UserController(@Value() RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

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
        return userService.getAllUsers();
    }

//    @GetMapping (value = "/me")
//    public ResponseEntity<User> getCurrentUser(Principal principal){
////        User userDetails = (User) principal;
//
//        restTemplate = new RestTemplate();
//
//        HttpHeaders httpHeaders= new HttpHeaders();
//        httpHeaders.add("Authorization", AccessToken.getAccessToken());
//        HttpEntity<User> userHttpEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:9191/users/".concat(principal.getName()), HttpMethod.GET, userHttpEntity, User.class);
//        return responseEntity;
//    }

    @GetMapping (value = "/me")
    public ResponseEntity<User> getCurrentUser(Principal principal){
//        User userDetails = (User) principal;

        System.out.println("Ussername ".concat(principal.getName()));


        User user = userService.getByUsername(principal.getName());
        return ResponseEntity.ok().body(user);
    }
}
