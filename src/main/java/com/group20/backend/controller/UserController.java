package com.group20.backend.controller;

import com.group20.backend.model.User;
import com.group20.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(User user) {
        userService.create(user);
    }

    @GetMapping
    public User getUserById(@RequestParam String id) throws ExecutionException, InterruptedException {
        Optional<User> user = userService.findById(id);
        return user.orElse(null);
    }

}
