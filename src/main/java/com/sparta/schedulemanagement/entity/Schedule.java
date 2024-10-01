package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class Schedule {


    private Long id;

    public String username;

    private String password;

    public String title;

    public String content;

    @CreatedDate
    public LocalDate date;

    @LastModifiedDate
    public LocalDate updatedDate;

    public Schedule(ScheduleRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

    }



}
