package com.sparta.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserRequestDto {
    public String username;
    public String email;
    private LocalDate date;
    private LocalDate updated;

}
