package com.hotelchallenge.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hotelchallenge.TestApplication;
import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.dto.UserDTO;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.UserRepository;
import java.util.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

public class UserRestTest extends TestApplication {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testUserRegister() throws Exception {
        final UserDTO userDTO = new UserDTO("test@test.com", "Dimitar Gavrilov", "testPassword");

        mockMvc.perform(MockMvcRequestBuilders.post(RestRouter.User.REGISTER)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userDTO)))
                .andExpect(status().isCreated());

        final Optional<User> user = userRepository.findByEmail("test@test.com");
        assertThat(user.isPresent()).isTrue();
    }
}