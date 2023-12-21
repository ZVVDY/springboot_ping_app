package com.example.springboot_ping_app.controller;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.entity.Ping;
import com.example.springboot_ping_app.service.PingService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ping")
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<PingDto> resultsPage = pingService.getResultsWithPagination(page);
        List<PingDto> results = resultsPage.getContent();

        model.addAttribute("results", results);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resultsPage.getTotalPages());

        return "pingResults";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Optional result = pingService.getResultById(id);

        model.addAttribute("result", result);
        return "pingResultDetails";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query,
                         @RequestParam(required = false) LocalDate fromDate,
                         @RequestParam(required = false) LocalDate toDate,
                         @RequestParam(required = false) String testStatus,
                         Model model) {

        List<Ping> results = pingService.search(query, fromDate, toDate, testStatus);

        model.addAttribute("results", results);

        return "pingResults";
    }
}
