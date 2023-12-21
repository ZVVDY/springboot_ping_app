package com.example.springboot_ping_app.service;

import com.example.springboot_ping_app.entity.PingResult;

import java.util.Date;
import java.util.List;

public interface PingResultService {
    List<PingResult> getAllResults();

    PingResult getResultById(Long id);

    List<PingResult> search(String query, Date fromDate, Date toDate, String testStatus);
}
