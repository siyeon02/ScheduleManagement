package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.UserResponseDto;
import com.sparta.schedulemanagement.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/user/{username}/{email}")
    public UserResponseDto getUserInfo(@PathVariable String username, @PathVariable String email) {
        UserService userService = new UserService(jdbcTemplate);
        return userService.getUserInfo(username, email);

    }
}
