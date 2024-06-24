
package com.Assignment.FileStorageSys.Services;

import com.Assignment.FileStorageSys.Dtos.UserDto;
import com.Assignment.FileStorageSys.Factories.UserFactory;
import com.Assignment.FileStorageSys.Models.User;
import com.Assignment.FileStorageSys.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserFactory userFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userFactory = userFactory;
    }

    @Override
    public User registerUser(UserDto userDTO) {
        User user = userFactory.createUser(userDTO, passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();

    }
}


