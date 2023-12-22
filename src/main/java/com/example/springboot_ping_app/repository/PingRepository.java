package com.example.springboot_ping_app.repository;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.entity.Ping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PingRepository extends PagingAndSortingRepository<Ping, Long> {
    Page <Ping> findAll(Pageable pageable);
    List<Ping> findPingByIpAddress(String ipAddress);
    List<Ping> findByDomain(String domain);
}
