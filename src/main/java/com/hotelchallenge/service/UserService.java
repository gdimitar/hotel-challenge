package com.hotelchallenge.service;

import com.hotelchallenge.dto.UserDTO;
import com.hotelchallenge.mapper.UserMapper;
import com.hotelchallenge.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;

    public UserService(final UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User createUser(final UserDTO userDTO) {
        return userMapper.createUser(userDTO);
    }
}
