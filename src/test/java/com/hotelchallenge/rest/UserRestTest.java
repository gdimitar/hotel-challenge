package com.hotelchallenge.rest;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hotelchallenge.TestApplication;
import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.constants.RoleConstants;
import com.hotelchallenge.data.UserData;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.UserRepository;
import java.util.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

public class UserRestTest extends TestApplication {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testUserRegister() throws Exception {
        final UserData userData = new UserData("test@test.com", "Dimitar Gavrilov", "testPassword");

        mockMvc.perform(MockMvcRequestBuilders.post(RestRouter.User.REGISTER)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userData)))
                .andExpect(status().isCreated());

        final Optional<User> user = userRepository.findByEmail("test@test.com");
        assertTrue(user.isPresent());
        assertEquals("test@test.com", user.get().getEmail());
        assertEquals("Dimitar Gavrilov", user.get().getDisplayName());
        assertEquals(1, user.get().getRoles().size());
        assertEquals(RoleConstants.ROLE_REGULAR_USER, user.get().getRoles().iterator().next().getName());
    }

    @Test
    @Transactional
    public void testUserAlreadyRegistered() throws Exception {
        final UserData userData = new UserData("test@test.com", "Dimitar Gavrilov", "testPassword");

        mockMvc.perform(MockMvcRequestBuilders.post(RestRouter.User.REGISTER)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userData)))
                .andExpect(status().isCreated());

        final UserData newUserData = new UserData("test@test.com", "Dimitar1 Gavrilov1", "testPassword1");
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(RestRouter.User.REGISTER)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(newUserData)))
                .andExpect(status().isBadRequest()).andReturn();

        assertEquals("email address already in use", mvcResult.getResponse().getContentAsString());
    }
}