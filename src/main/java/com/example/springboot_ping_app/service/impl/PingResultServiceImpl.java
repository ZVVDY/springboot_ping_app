package com.example.springboot_ping_app.service.impl;

import com.example.springboot_ping_app.entity.PingResult;
import com.example.springboot_ping_app.service.PingResultService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PingResultServiceImpl implements PingResultService {
    @Override
    public List<PingResult> getAllResults() {
        return null;
    }

    @Override
    public PingResult getResultById(Long id) {
        return null;
    }

    @Override
    public List<PingResult> search(String query, Date fromDate, Date toDate, String testStatus) {
        return null;
    }
}
