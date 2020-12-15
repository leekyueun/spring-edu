package com.kyueun.apis.service;

import com.kyueun.apis.model.User;
import com.kyueun.apis.repository.UserRepository;
import com.kyueun.apis.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(int userId) throws Exception {
        Optional<User> searchedUser = this.userRepository.findById(userId);
        return searchedUser.orElseThrow(() -> new Exception("해당 유저를 찾지 못하였습니다"));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
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

    public void createUser(UserRegisterVO userRegisterVO) {
        User createUser = User.builder()
                .name(userRegisterVO.getName())
                .phone(userRegisterVO.getPhone())
                .email(userRegisterVO.getEmail())
                .build();
        this.userRepository.save(createUser);
        this.userRepository.flush();
    }

    public void deleteUser(int userId) {
        this.userRepository.deleteById(userId);
    }
}
