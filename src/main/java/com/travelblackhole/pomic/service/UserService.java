package com.travelblackhole.pomic.service;

import com.travelblackhole.pomic.dto.UserDto;
import com.travelblackhole.pomic.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> register(User user);
    Optional<UserDto> login(String username, String password);
    Optional<UserDto> getUserById(Long id);
    Optional<UserDto> updateUser(Long id, UserDto userDto);
    Optional<UserDto> deleteUser(Long id);

}
