package com.example.springboot_ping_app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class PingSearchDto {
    @NotNull
    @Size(min = 4, max = 255, message = "Максимальная длина IP-адреса или домена -от 4 до 255 символов")
    private String ipAddressOrDomain;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull
    @Pattern(regexp = "^(success|failure)$", message = "Статус должен быть 'success' или 'failure'")
    private String testStatus;
}
