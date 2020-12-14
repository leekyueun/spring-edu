package com.kyueun.apis.route;

import com.kyueun.apis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class User {
    private final UserService userService;

    @Autowired
    public User(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public void getUsers() {
        this.userService.findAll();
    }

    @GetMapping("/initialize")
    public void initializeUsers() {
        this.userService.initializeUsers();
    }
}
