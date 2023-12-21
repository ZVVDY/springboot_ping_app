package com.example.springboot_ping_app.repository;

import com.example.springboot_ping_app.entity.PingResult;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingResultRepository extends PagingAndSortingRepository<PingResult, Long> {

}
