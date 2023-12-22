package com.example.springboot_ping_app.controller;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.service.PingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PingControllerTest {

    @Mock
    private PingService pingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private PingController pingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testList() {
        int page = 1;
        Page<PingDto> resultsPage = mock(Page.class);
        List<PingDto> results = new ArrayList<>();
        when(resultsPage.getContent()).thenReturn(results);
        when(pingService.getResultsWithPagination(page)).thenReturn(resultsPage);

        String viewName = pingController.list(model, page);

        verify(model).addAttribute("results", results);
        verify(model).addAttribute("currentPage", page);
        verify(model).addAttribute("totalPages", resultsPage.getTotalPages());
        assertEquals("pingResults", viewName);
    }

    @Test
    void testShowSearchForm() {
        String viewName = pingController.showSearchForm(model);

        verify(model).addAttribute(eq("pingSearchDto"), any(PingSearchDto.class));
        assertEquals("pingSearch", viewName);
    }

    @Test
    void testSearchWithValidDto() {
        PingSearchDto pingSearchDto = new PingSearchDto();
        List<PingDto> results = new ArrayList<>();
        when(pingService.search(pingSearchDto)).thenReturn(results);

        String viewName = pingController.search(pingSearchDto, bindingResult, model);

        verify(model).addAttribute("results", results);
        assertEquals("pingResultDetails", viewName);
    }

    @Test
    void testSearchWithInvalidDto() {
        PingSearchDto pingSearchDto = new PingSearchDto();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = pingController.search(pingSearchDto, bindingResult, model);

        verify(model).addAttribute("org.springframework.validation.BindingResult.pingSearchDto",
                bindingResult);
        assertEquals("pingSearch", viewName);
    }
}