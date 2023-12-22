package com.example.springboot_ping_app.service;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.entity.Ping;
import org.springframework.data.domain.Page;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PingService {
    List<PingDto> searchToDb(PingSearchDto pingSearchDto);

    Page<PingDto> getResultsWithPagination(int page);

    List<PingDto> search(PingSearchDto pingSearchDto);
}
