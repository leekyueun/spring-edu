package com.kyueun.apis.service;

import com.kyueun.apis.model.User;
import com.kyueun.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void findAll() {
        for (User user : this.userRepository.findAll()) {
            System.out.println(user);
        }
    }

    public void initializeUsers() {
        User user1 = User.builder()
                .email("examplo1@sample.com")
                .name("Mr. Example")
                .phone("01000000000")
                .build();

        User user2 = User.builder()
                .email("examplo2@sample.com")
                .name("Mr. Sample")
                .phone("01000001234")
                .build();

        User user3 = User.builder()
                .email("examplo3@sample.com")
                .name("Mr. Sample Data")
                .phone("01012341234")
                .build();

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.flush();
    }
}
