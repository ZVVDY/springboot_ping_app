package com.example.springboot_ping_app.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;


@Data
public class PingDto {
    private Long id;
    @Pattern(regexp = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")
    private String ipAddress;
    @Pattern(regexp = "^(?:(?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$")
    private String domain;
    private LocalDate checkDate;
    private String testStatus;
    private String pingResult;
}
