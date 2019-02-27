package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.dto.UserDTO;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.UserRepository;
import com.hotelchallenge.service.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRest {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserRest(final UserRepository userRepository, final UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping(path = RestRouter.User.REGISTER,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity registerUser(final @Valid @RequestBody UserDTO userDTO) {

        final HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        return userRepository.findByEmail(userDTO.getEmail())
                .map(user -> new ResponseEntity<>("email address already in use", textPlainHeaders,
                        HttpStatus.BAD_REQUEST))
                .orElseGet(() -> {
                    final User user = userService.createUser(userDTO);
                    userRepository.save(user);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }


}
