package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);
        return scheduleResponseDto;


    }

    public List<ScheduleResponseDto> getSchedule() {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findAll();

    }

    public ScheduleResponseDto getUserSchedule(Long id) {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findByUser(id);

    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule schedule = scheduleRepository.findById(id);
        if(schedule!= null){
            scheduleRepository.update(id, requestDto);

            return id;

        }else{
            throw new IllegalArgumentException("선택한 스케쥴은 존재하지 않습니다.");
        }

    }

    public Long deleteSchedule(Long id) {

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null){
            scheduleRepository.delete(id);

            return id;

        }else{
            throw new IllegalArgumentException("선택한 스케쥴은 존제하지 않습니다.");
        }
    }

}
