package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    public String name;
    public String title;
    public String content;
    public LocalDateTime date;
    public LocalDateTime updatedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = getId();
        this.title = getTitle();
        this.content = getContent();
        this.date = getDate();
        this.updatedDate = getUpdatedDate();
    }


    public ScheduleResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
