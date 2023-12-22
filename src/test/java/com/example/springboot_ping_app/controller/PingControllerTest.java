package com.example.springboot_ping_app.controller;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.service.PingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PingControllerTest {

    @Mock
    private PingService pingService;

    @Mock
    private Model model;

    @Mock
    private Page<PingDto> resultsPage;

    private PingController pingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pingController = new PingController(pingService);
    }

    @Test
    void testList() {
        int page = 1;
        List<PingDto> results = Collections.singletonList(new PingDto());

        when(pingService.getResultsWithPagination(page)).thenReturn(resultsPage);
        when(resultsPage.getContent()).thenReturn(results);

        String viewName = pingController.list(model, page);

        assertEquals("pingResults", viewName);
        verify(model).addAttribute("results", results);
        verify(model).addAttribute("currentPage", page);
        verify(model).addAttribute("totalPages", resultsPage.getTotalPages());
    }

    @Test
    void testSearch_WithNoErrors() {
        PingSearchDto pingSearchDto = new PingSearchDto();
        List<PingDto> results = Collections.singletonList(new PingDto());

        when(pingService.search(pingSearchDto)).thenReturn(results);

        String viewName = pingController.search(pingSearchDto, mock(BindingResult.class), model);

        assertEquals("pingResultDetails", viewName);
        verify(model).addAttribute("results", results);
    }

    @Test
    void testSearch_WithErrors() {
        PingSearchDto pingSearchDto = new PingSearchDto();
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = pingController.search(pingSearchDto, bindingResult, model);

        assertEquals("pingSearch", viewName);
        verify(model).addAttribute(eq("org.springframework.validation.BindingResult.pingSearchDto"), any());
    }
}