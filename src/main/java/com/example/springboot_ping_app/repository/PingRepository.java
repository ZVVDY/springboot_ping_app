package com.example.springboot_ping_app.repository;

import com.example.springboot_ping_app.entity.Ping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingRepository extends PagingAndSortingRepository<Ping, Long> {
    Page <Ping> findAll(Pageable pageable);
}
