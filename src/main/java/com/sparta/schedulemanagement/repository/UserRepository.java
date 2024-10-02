package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.dto.UserResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserResponseDto findByUsername(String username, String email) {
        String sql = "SELECT * FROM user WHERE username =? AND email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, email},new RowMapper<UserResponseDto>() {

            @Override
            public UserResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                String username = rs.getString("username");
                String email = rs.getString("email");
                Date date = rs.getDate("date");
                LocalDate localDate = (date != null) ? date.toLocalDate() : null;
                //LocalDate date = rs.getDate("date").toLocalDate();
                Date updated = rs.getDate("updated");
                LocalDate localupdated = (date != null) ? updated.toLocalDate() : null;

                return new UserResponseDto(username, email, localDate, localupdated);

            }
        });
    }
}
