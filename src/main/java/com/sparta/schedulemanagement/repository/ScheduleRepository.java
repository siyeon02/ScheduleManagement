package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;


public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Schedule save(Schedule schedule){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (username, password, title, content, date) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1,schedule.getUsername());
                    preparedStatement.setString(2,schedule.getPassword());
                    preparedStatement.setString(3,schedule.getTitle());
                    preparedStatement.setString(4, schedule.getContent());
                    preparedStatement.setDate(5, Date.valueOf(schedule.getDate()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;

    }

    public List<ScheduleResponseDto> findAll(int page, int size) {

        int offset = page * size;

        String sql = "SELECT * FROM schedule LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new Object[]{size, offset}, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String title = rs.getString("title");
                String content = rs.getString("content");
                // null 체크 후 LocalDate 변환
                Date date = rs.getDate("date");
                LocalDate localDate = (date != null) ? date.toLocalDate() : null;
                Date updatedDate = rs.getDate("updatedDate");
                LocalDate localupdatedDate = (updatedDate != null) ? updatedDate.toLocalDate() : null;

                return new ScheduleResponseDto(id, username, title, content, localDate, localupdatedDate);
                /*LocalDate date = rs.getDate("date").toLocalDate();
                return new ScheduleResponseDto(id, username, title, content, date);*/

            }
        });
    }

    public ScheduleResponseDto findByUser(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},new RowMapper<ScheduleResponseDto>() {

            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDate date = rs.getDate("date").toLocalDate();
                Date updatedDate = rs.getDate("updatedDate");
                LocalDate localupdatedDate = (updatedDate != null) ? updatedDate.toLocalDate() : null;
                return new ScheduleResponseDto(id, username, title, content, date, localupdatedDate);

            }
        });

    }

    public LocalDate update(Long id, String password, ScheduleRequestDto requestDto, LocalDate updatedDate) {
        String passwordsql = "SELECT password FROM schedule WHERE id = ?";
        String dbpassword = jdbcTemplate.queryForObject(passwordsql, new Object[]{id}, String.class);

        if(dbpassword != null && dbpassword.equals(password)){
            String sql = "UPDATE schedule SET username = ?, title = ? , content = ?, updated_date = ? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getTitle(), requestDto.getContent(), updatedDate, id);
            return updatedDate;
        }else{
            throw new IllegalArgumentException("Wrong password");
        }

    }

    public void delete(Long id, String password) {
        String passwordsql = "SELECT password FROM schedule WHERE id = ?";
        String dbpassword = jdbcTemplate.queryForObject(passwordsql, new Object[]{id}, String.class);

        if(dbpassword != null && dbpassword.equals(password)){
            String sql = "DELETE FROM schedule WHERE id = ?";
            jdbcTemplate.update(sql,id);
        }else{
            throw new IllegalArgumentException("Wrong password");
        }

    }

    public Schedule findById(Long id) {
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
