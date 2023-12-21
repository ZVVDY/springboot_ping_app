package com.example.springboot_ping_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Ping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ip_address")
    private String ipAddress;

    private String domain;
    @Column(name = "check_date")
    private LocalDate checkDate;
    @Column(name = "test_status")
    private String testStatus;
    @Column(name = "ping_result", columnDefinition = "TEXT")
    private String pingResult;
}
