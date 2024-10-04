package com.sparta.schedulemanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {
    public String password;
    public String title;
    public String content;
    public String username;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate date;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate updatedDate;
}
