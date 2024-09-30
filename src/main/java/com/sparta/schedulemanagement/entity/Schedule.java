package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class Schedule {


    private Long id;

    public String title;

    public String content;

    @CreatedDate
    public Date date;

    @LastModifiedDate
    public Date updatedDate;

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

    }


    public void createSchedule(){

    }

    public void updatedSchedule(){

    }

    public void deleteSchedule(){

    }

}
