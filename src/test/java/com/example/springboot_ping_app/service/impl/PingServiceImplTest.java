package com.example.springboot_ping_app.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.entity.Ping;
import com.example.springboot_ping_app.mapper.PingMapper;
import com.example.springboot_ping_app.repository.PingRepository;
import com.example.springboot_ping_app.util.IpDomainValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class PingServiceImplTest {

    private PingRepository pingRepository;
    private PingMapper pingMapper;
    private IpDomainValidator ipDomainValidator;
    private PingServiceImpl pingService;

    @BeforeEach
    void setUp() {
        pingRepository = mock(PingRepository.class);
        pingMapper = mock(PingMapper.class);
        ipDomainValidator = mock(IpDomainValidator.class);
        pingService = new PingServiceImpl(pingRepository, pingMapper, ipDomainValidator);
    }

    @Test
    void testGetResultsWithPagination() {
        Ping ping1 = new Ping();
        ping1.setId(1L);
        ping1.setDomain("example1.com");

        Ping ping2 = new Ping();
        ping2.setId(2L);
        ping2.setDomain("example2.com");

        List<Ping> pingList = Arrays.asList(ping1, ping2);

        Page<Ping> page = new PageImpl<>(pingList);

        when(pingRepository.findAll(any(Pageable.class))).thenReturn(page);

        PingDto pingDto1 = new PingDto();
        pingDto1.setId(1L);
        pingDto1.setDomain("example1.com");

        PingDto pingDto2 = new PingDto();
        pingDto2.setId(2L);
        pingDto2.setDomain("example2.com");

        when(pingMapper.toDto(any(Ping.class))).thenReturn(pingDto1, pingDto2);

        Page<PingDto> result = pingService.getResultsWithPagination(1);

        assertEquals(2, result.getTotalElements());
        assertEquals(2, result.getContent().size());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals("example1.com", result.getContent().get(0).getDomain());
        assertEquals(2L, result.getContent().get(1).getId());
        assertEquals("example2.com", result.getContent().get(1).getDomain());
    }

    @Test
    void testSearchIp() {
        PingSearchDto searchDto = new PingSearchDto();
        searchDto.setIpAddressOrDomain("192.168.1.1");

        Ping ping = new Ping();
        ping.setId(1L);
        ping.setIpAddress(searchDto.getIpAddressOrDomain());

        when(ipDomainValidator.isIpName(eq(searchDto.getIpAddressOrDomain()))).thenReturn(true);

        when(pingRepository.findPingByIpAddress(eq(searchDto.getIpAddressOrDomain()))).thenReturn(Collections.singletonList(ping));

        when(pingMapper.modelsToDto(anyList())).thenReturn(Collections.singletonList(new PingDto()));

        List<PingDto> result = pingService.searchToDb(searchDto);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(searchDto.getIpAddressOrDomain(), result.get(0).getIpAddress());
    }

    @Test
    void testSearchDomain() {
        PingSearchDto searchDto = new PingSearchDto();
        searchDto.setIpAddressOrDomain("example.com");

        Ping ping = new Ping();
        ping.setId(1L);
        ping.setDomain(searchDto.getIpAddressOrDomain());

        when(ipDomainValidator.isIpName(eq(searchDto.getIpAddressOrDomain()))).thenReturn(false);

        when(pingRepository.findByDomain(eq(searchDto.getIpAddressOrDomain()))).thenReturn(Collections.singletonList(ping));

        when(pingMapper.modelsToDto(anyList())).thenReturn(Collections.singletonList(new PingDto()));

        List<PingDto> result = pingService.search(searchDto);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(searchDto.getIpAddressOrDomain(), result.get(0).getDomain());
    }
}

