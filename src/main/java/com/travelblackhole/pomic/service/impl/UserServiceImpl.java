package com.travelblackhole.pomic.service.impl;

import com.travelblackhole.pomic.dto.UserDto;
import com.travelblackhole.pomic.repository.UserRepository;
import com.travelblackhole.pomic.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDto> getUserById(Long id){
        return userRepository.findById(id)
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setNickname(user.getNickname());
                    userDto.setCreatedBy(user.getCreatedBy());
                    return userDto;
                });
    }
}
