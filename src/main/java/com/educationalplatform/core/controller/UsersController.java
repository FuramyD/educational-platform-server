package com.educationalplatform.core.controller;

import com.educationalplatform.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/users")
public class UsersController {

//    @Autowired
//    UsersService usersService

    @GetMapping("/")
    public User getUserById(@RequestParam(value = "id", defaultValue = "0") String id) {
        return new User();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User body) {
        return body;
    }

}
