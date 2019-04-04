package com.hotelchallenge.service;

import com.hotelchallenge.data.UserData;
import com.hotelchallenge.mapper.UserMapper;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserService(final UserMapper userMapper, final UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public User createUser(final UserData userData) {
        return userMapper.createUser(userData);
    }

    public Optional<User> findUserById(final long userId) {
        return userRepository.findById(userId);
    }

}
