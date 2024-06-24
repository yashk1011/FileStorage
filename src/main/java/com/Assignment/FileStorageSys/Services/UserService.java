package com.Assignment.FileStorageSys.Services;

import com.Assignment.FileStorageSys.Dtos.UserDto;
import com.Assignment.FileStorageSys.Models.User;

public interface UserService {
    User registerUser(UserDto userDTO);
    User findByUsername(String username);

}
