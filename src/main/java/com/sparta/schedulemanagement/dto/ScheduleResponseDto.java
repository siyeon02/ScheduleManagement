package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    public String username;
    public String title;
    public String content;
    public LocalDate date;
    public LocalDate updatedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.username = schedule.getUsername();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
        this.updatedDate = schedule.getUpdatedDate();
    }


    public ScheduleResponseDto(Long id, String username, String title, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }


}
