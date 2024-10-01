package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    public String name;

    public UserResponseDto(User user) {
        this.name = user.getName();
    }
}
