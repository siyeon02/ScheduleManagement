package com.sparta.schedulemanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {

    public String username;
    public String email;
    private LocalDate date;
    private LocalDate updatedDate;

}
