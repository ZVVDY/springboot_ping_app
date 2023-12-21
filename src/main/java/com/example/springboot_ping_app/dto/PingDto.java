package com.example.springboot_ping_app.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class PingDto {
    private Long id;
    private String ipAddress;
    private String domain;
    private LocalDate checkDate;
    private String testStatus;
    private String pingResult;
}
