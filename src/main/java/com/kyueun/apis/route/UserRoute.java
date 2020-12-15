package com.kyueun.apis.route;

import com.kyueun.apis.model.User;
import com.kyueun.apis.service.UserService;
import com.kyueun.apis.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRoute {
    private final UserService userService;

    @Autowired
    public UserRoute(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    @GetMapping("/{user_id}")
    @ResponseBody
    public User getUser(@PathVariable(value = "user_id") String userId) throws Exception {
        return this.userService.find(Integer.parseInt(userId));
    }

    @PostMapping("")
    public void createUser(UserRegisterVO user) {
        this.userService.createUser(user);
    }

    @GetMapping("/initialize")
    public void initializeUsers() {
        this.userService.initializeUsers();
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable(value = "user_id") String userId) {
        this.userService.deleteUser(Integer.parseInt(userId));
    }
}
