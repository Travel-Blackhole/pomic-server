package com.travelblackhole.pomic.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String nickname;
    private LocalDateTime createdBy;
}
