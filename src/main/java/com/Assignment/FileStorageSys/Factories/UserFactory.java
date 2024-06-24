package com.Assignment.FileStorageSys.Factories;

import com.Assignment.FileStorageSys.Dtos.UserDto;
import com.Assignment.FileStorageSys.Models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(UserDto userDTO, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
