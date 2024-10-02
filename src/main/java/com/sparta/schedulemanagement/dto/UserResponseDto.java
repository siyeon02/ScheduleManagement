package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDto {

    public String username;
    public String email;
    private LocalDate date;
    private LocalDate updated;

    public UserResponseDto(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.date = user.getDate();
        this.updated = user.getUpdatedDate();
    }

    public UserResponseDto(String username, String email, LocalDate date, LocalDate updated) {
        this.username = username;
        this.email = email;
        this.date = date;
        this.updated= updated;
    }
}
