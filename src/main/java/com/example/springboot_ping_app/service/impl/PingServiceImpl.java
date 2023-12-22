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

@Service
public class PingServiceImpl implements PingService {

    private final PingRepository pingRepository;

    private final PingMapper pingMapper;
    private final IpDomainValidator ipDomainValidator;

    @Autowired
    public PingServiceImpl(PingRepository pingRepository, PingMapper pingMapper, IpDomainValidator ipDomainValidator) {
        this.pingRepository = pingRepository;
        this.pingMapper = pingMapper;
        this.ipDomainValidator = ipDomainValidator;
    }

    @Override
    public Page<PingDto> getResultsWithPagination(int page) {
        Pageable firstPageWithFiveElements = PageRequest.of(page - 1, 5, Sort.by("domain")
                .ascending());
        Page<Ping> pagePing = pingRepository.findAll(firstPageWithFiveElements);
        return pagePing.map(pingMapper::toDto);
    }

    @Override
    public List<PingDto> search(PingSearchDto pingSearchDto) {
        Optional<PingSearchDto> optionalPingSearchDto = Optional.of(pingSearchDto);
        PingSearchDto pingSearch = optionalPingSearchDto.get();
        List<PingDto> pingDtos = searchToDb(pingSearchDto);
        return pingDtos;
    }


    public List<PingDto> searchToDb(PingSearchDto pingSearchDto) {
        if (ipDomainValidator.isIpName((pingSearchDto.getIpAddressOrDomain()))) {
            List<Ping> pings = pingRepository.findPingByIpAddress(pingSearchDto.getIpAddressOrDomain());
            List<PingDto> pingDtos = pingMapper.modelsToDto(pings);
            return pingDtos;
        } else {
            List<Ping> pings1 = pingRepository.findByDomain(pingSearchDto.getIpAddressOrDomain());
            List<PingDto> pingDtos = pingMapper.modelsToDto(pings1);
            return pingDtos;
        }

    }
}
