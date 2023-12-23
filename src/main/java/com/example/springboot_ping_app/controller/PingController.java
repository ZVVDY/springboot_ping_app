package com.example.springboot_ping_app.controller;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.dto.PingSearchDto;
import com.example.springboot_ping_app.service.PingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/ping")
public class PingController {

    private final PingService pingService;
    private static final Logger logger = Logger.getLogger(PingController.class.getName());

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1") int page) {
        try {
            Page<PingDto> resultsPage = pingService.getResultsWithPagination(page);
                List<PingDto> results = resultsPage.getContent();
                model.addAttribute("results", results);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", resultsPage.getTotalPages());

            return "pingResults";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while retrieving results", e);
            throw new RuntimeException("Error occurred while retrieving results", e);
        }
    }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("pingSearchDto", new PingSearchDto());
        return "pingSearch";
    }

    @PostMapping(value = "/search", produces = "text/html; charset=UTF-8")
    public String search(@Valid @ModelAttribute("pingSearchDto") PingSearchDto pingSearchDto,
                         BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("org.springframework.validation.BindingResult.pingSearchDto",
                        bindingResult);
                return "pingSearch";
            }

            List<PingDto> results = pingService.search(pingSearchDto);
            model.addAttribute("results", results);

            return "pingResultDetails";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred while performing search", e);
            throw new RuntimeException("Error occurred while performing search", e);
        }
    }
}