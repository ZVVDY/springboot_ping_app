package com.example.springboot_ping_app.service.impl;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.entity.Ping;
import com.example.springboot_ping_app.mapper.PingMapper;
import com.example.springboot_ping_app.repository.PingRepository;
import com.example.springboot_ping_app.util.IpDomainValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PingServiceImplTest {

    @Mock
    private PingRepository pingRepository;

    @Mock
    private PingMapper pingMapper;

    @Mock
    private IpDomainValidator ipDomainValidator;

    @InjectMocks
    private PingServiceImpl pingService;

    @Test
    public void testGetResultsWithPagination() {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("domain").ascending());
        List<Ping> pings = new ArrayList<>();
        pings.add(new Ping());
        pings.add(new Ping());
        Page<Ping> pagePing = new PageImpl<>(pings, pageable, pings.size());

        when(pingRepository.findAll(pageable)).thenReturn(pagePing);
        when(pingMapper.toDto(any(Ping.class))).thenReturn(new PingDto());

        Page<PingDto> result = pingService.getResultsWithPagination(page);

        assertEquals(2, result.getContent().size());

        verify(pingRepository, times(1)).findAll(pageable);
        verify(pingMapper, times(2)).toDto(any(Ping.class)); // Verify the number of invocations
    }

    @Test
    public void testSearchWithIpAddress() {
        PingSearchDto pingSearchDto = new PingSearchDto();
        pingSearchDto.setIpAddressOrDomain("127.0.0.1");

        when(ipDomainValidator.isIpName(pingSearchDto.getIpAddressOrDomain())).thenReturn(true);

        List<Ping> pings = new ArrayList<>();
        pings.add(new Ping());
        when(pingRepository.findPingByIpAddress(pingSearchDto.getIpAddressOrDomain())).thenReturn(pings);

        List<PingDto> pingDtos = new ArrayList<>();
        pingDtos.add(new PingDto());
        when(pingMapper.modelsToDto(pings)).thenReturn(pingDtos);

        List<PingDto> result = pingService.search(pingSearchDto);

        assertEquals(pingDtos, result);
        verify(pingRepository, times(1)).findPingByIpAddress(pingSearchDto.getIpAddressOrDomain());
        verify(pingMapper, times(1)).modelsToDto(pings);
    }

    @Test
    public void testSearchWithDomain() {
        PingSearchDto pingSearchDto = new PingSearchDto();
        pingSearchDto.setIpAddressOrDomain("example.com");

        when(ipDomainValidator.isIpName(pingSearchDto.getIpAddressOrDomain())).thenReturn(false);

        List<Ping> pings = new ArrayList<>();
        pings.add(new Ping());
        when(pingRepository.findByDomain(pingSearchDto.getIpAddressOrDomain())).thenReturn(pings);

        List<PingDto> pingDtos = new ArrayList<>();
        pingDtos.add(new PingDto());
        when(pingMapper.modelsToDto(pings)).thenReturn(pingDtos);

        List<PingDto> result = pingService.search(pingSearchDto);

        assertEquals(pingDtos, result);
        verify(pingRepository, times(1)).findByDomain(pingSearchDto.getIpAddressOrDomain());
        verify(pingMapper, times(1)).modelsToDto(pings);
    }
}