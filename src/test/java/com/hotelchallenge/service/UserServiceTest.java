package com.hotelchallenge.service;

import static org.mockito.Mockito.*;

import com.hotelchallenge.dto.UserDTO;
import com.hotelchallenge.mapper.UserMapper;
import com.hotelchallenge.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserMapper userMapper;
    @Mock
    private User user;

    private final UserDTO userDTO = new UserDTO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        doReturn(user).when(userMapper).createUser(userDTO);
    }

    @Test
    public void testRegisterUser() {
        service.createUser(userDTO);

        verify(userMapper).createUser(userDTO);
    }
}