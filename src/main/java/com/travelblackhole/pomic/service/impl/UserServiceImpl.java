package com.travelblackhole.pomic.service.impl;

import com.travelblackhole.pomic.dto.UserDto;
import com.travelblackhole.pomic.entity.User;
import com.travelblackhole.pomic.entity.UserStatus;
import com.travelblackhole.pomic.repository.UserRepository;
import com.travelblackhole.pomic.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Optional<UserDto> register(User user) {

        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedBy(LocalDateTime.now());
        user.setUpdatedBy(LocalDateTime.now());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return Optional.of(toDto(savedUser));
    }

    @Override
    public Optional<UserDto> login(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .map(this::toDto);
    }


    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDto.getUsername());
                    user.setNickname(userDto.getNickname());
                    user.setUpdatedBy(LocalDateTime.now());

                    User updatedUser = userRepository.save(user);
                    return toDto(updatedUser);
                });
    }

    @Override
    public Optional<UserDto> deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return toDto(user);
                });
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setNickname(user.getNickname());
        dto.setCreatedBy(user.getCreatedBy());
        return dto;
    }
}








