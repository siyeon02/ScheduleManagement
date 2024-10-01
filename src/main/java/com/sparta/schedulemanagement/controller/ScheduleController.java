package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
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
        Schedule schedule = new Schedule(requestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (username, password, title, content) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1,schedule.getUsername());
                    preparedStatement.setString(2,schedule.getPassword());
                    preparedStatement.setString(3,schedule.getTitle());
                    preparedStatement.setString(4, schedule.getContent());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);


        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule(){
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id");
                    String username = rs.getString("username");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    //LocalDate date = rs.getDate("date").toLocalDate();
                    return new ScheduleResponseDto(id, username, title, content);

            }
        });
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getUserSchedule(@PathVariable Long id){
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String title = rs.getString("title");
                String content = rs.getString("content");
                //LocalDate date = rs.getDate("date").toLocalDate();
                return new ScheduleResponseDto(id, username, title, content);

            }
        });

    }

    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = findById(id);
        if(schedule!= null){
            String sql = "UPDATE schedule SET username = ?, title = ? , content = ? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getTitle(), requestDto.getContent(), id);
            return id;

        }else{
            throw new IllegalArgumentException("선택한 스케쥴은 존재하지 않습니다.");
        }

    }

    @DeleteMapping("/schedule/{id}")
    public Long deleteSchedule(@PathVariable Long id){
        Schedule schedule = findById(id);
        if(schedule != null){
            String sql = "DELETE FROM schedule WHERE id = ?";
            jdbcTemplate.update(sql,id);
            return id;

        }else{
            throw new IllegalArgumentException("선택한 스케쥴은 존제하지 않습니다.");
        }
    }

    private Schedule findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()){
                Schedule schedule = new Schedule();
                schedule.setUsername(resultSet.getString("username"));
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContent(resultSet.getString("content"));
                return schedule;
            }else{
                return null;
            }
        }, id);
    }


}
