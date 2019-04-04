package com.hotelchallenge.service;

import static org.mockito.Mockito.*;

import com.hotelchallenge.data.UserData;
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

    private final UserData userData = new UserData();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        doReturn(user).when(userMapper).createUser(userData);
    }

    @Test
    public void testRegisterUser() {
        service.createUser(userData);

        verify(userMapper).createUser(userData);
    }
}