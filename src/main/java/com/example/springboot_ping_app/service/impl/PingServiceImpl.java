package com.example.springboot_ping_app.service.impl;
import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.entity.Ping;
import com.example.springboot_ping_app.mapper.PingMapper;
import com.example.springboot_ping_app.repository.PingRepository;
import com.example.springboot_ping_app.service.PingService;
import com.example.springboot_ping_app.util.IpDomainValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PingServiceImpl implements PingService {

    private final PingRepository pingRepository;
    private final PingMapper pingMapper;
    private final IpDomainValidator ipDomainValidator;

    private static final Logger logger = Logger.getLogger(PingServiceImpl.class.getName());

    @Autowired
    public PingServiceImpl(PingRepository pingRepository, PingMapper pingMapper, IpDomainValidator ipDomainValidator) {
        this.pingRepository = pingRepository;
        this.pingMapper = pingMapper;
        this.ipDomainValidator = ipDomainValidator;
    }

    @Override
    public Page<PingDto> getResultsWithPagination(int page) {
        try {
            Pageable firstPageWithFiveElements = PageRequest.of(page - 1, 5, Sort.by("domain").ascending());
            Page<Ping> pagePing = pingRepository.findAll(firstPageWithFiveElements);
            return pagePing.map(pingMapper::toDto);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving page results", e);
            throw new RuntimeException("Error occurred while retrieving page results", e);
        }
    }

    @Override
    public List<PingDto> search(PingSearchDto pingSearchDto) {
        Optional<PingSearchDto> optionalPingSearchDto = Optional.of(pingSearchDto);
        PingSearchDto pingSearch = optionalPingSearchDto.get();
        return searchToDb(pingSearchDto);
    }

    public List<PingDto> searchToDb(PingSearchDto pingSearchDto) {
        try {
            if (ipDomainValidator.isIpName(pingSearchDto.getIpAddressOrDomain())) {
                List<Ping> pings = pingRepository.findPingByIpAddress(pingSearchDto.getIpAddressOrDomain());
                return pingMapper.modelsToDto(pings);
            } else {
                List<Ping> pings1 = pingRepository.findByDomain(pingSearchDto.getIpAddressOrDomain());
                return pingMapper.modelsToDto(pings1);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while searching in the database", e);
            throw new RuntimeException("Error occurred while searching in the database", e);
        }
    }
}