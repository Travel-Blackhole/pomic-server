package com.travelblackhole.pomic.service;

import com.travelblackhole.pomic.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> getUserById(Long id);
}
