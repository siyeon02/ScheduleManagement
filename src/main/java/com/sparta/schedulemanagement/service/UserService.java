package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.UserResponseDto;
import com.sparta.schedulemanagement.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserService {
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserResponseDto getUserInfo(String username, String email) {

        UserRepository userRepository = new UserRepository(jdbcTemplate);
        return userRepository.findByUsername(username, email);

    }
}
