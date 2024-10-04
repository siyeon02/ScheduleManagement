package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.createSchedule(requestDto);

    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "3") int size){
        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);

        return scheduleService.getSchedule(page, size);

    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getUserSchedule(@PathVariable Long id){

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        return scheduleService.getUserSchedule(id);

    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        String password = requestDto.getPassword();
        //LocalDate updatedDate = scheduleService.updateSchedule(id, password, requestDto);

        ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, password, requestDto);
        return ResponseEntity.ok(updatedSchedule);

    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){

        ScheduleService scheduleService = new ScheduleService(jdbcTemplate);
        String password = requestDto.getPassword();
        return scheduleService.deleteSchedule(id, password);

    }



}
