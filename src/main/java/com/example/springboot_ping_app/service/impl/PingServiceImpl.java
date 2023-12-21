package com.example.springboot_ping_app.service.impl;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.entity.Ping;
import com.example.springboot_ping_app.mapper.PingMapper;
import com.example.springboot_ping_app.repository.PingRepository;
import com.example.springboot_ping_app.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PingServiceImpl implements PingService {

    private final PingRepository pingRepository;

    private final PingMapper pingMapper;

    @Autowired
    public PingServiceImpl(PingRepository pingRepository, PingMapper pingMapper) {
        this.pingRepository = pingRepository;
        this.pingMapper = pingMapper;
    }

    @Override
    public List<Ping> getAllResults() {
        return null;
    }

    @Override
    public Page<PingDto> getResultsWithPagination(int page) {
        Pageable firstPageWithFiveElements = PageRequest.of(page - 1, 5, Sort.by("domain")
                .ascending());
        Page<Ping> pagePing = pingRepository.findAll(firstPageWithFiveElements);
        return pagePing.map(pingMapper::toDto);
    }

    @Override
    public Optional<Ping> getResultById(Long id) {
        return null;
    }

    @Override
    public List<PingDto> search(PingSearchDto pingSearchDto) {


        return null;
    }
}
