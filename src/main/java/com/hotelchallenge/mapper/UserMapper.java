package com.hotelchallenge.mapper;

import com.hotelchallenge.constants.RoleConstants;
import com.hotelchallenge.dto.UserDTO;
import com.hotelchallenge.model.Role;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.RoleRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserMapper(final PasswordEncoder passwordEncoder, final RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User createUser(final UserDTO userDTO) {
        final User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setDisplayName(userDTO.getDisplayName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        final Set<Role> roles = new HashSet<>();

        final Role role = roleRepository.getOne(RoleConstants.ROLE_REGULAR_USER);
        roles.add(role);
        user.setRoles(roles);

        return user;
    }
}
