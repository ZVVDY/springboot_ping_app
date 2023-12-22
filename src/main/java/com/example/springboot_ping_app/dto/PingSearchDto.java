package com.example.springboot_ping_app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class PingSearchDto {
    @Size(min = 4, max = 255, message = "Длина IP-адреса или домена -от 4 до 255 символов")
    private String ipAddressOrDomain;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Поле не должно быть пустым")
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Поле не должно быть пустым")
    private LocalDate endDate;
    @NotNull(message = "Поле не должно быть пустым")
    //@Pattern(regexp = "^(success|failure)$", message = "Статус должен быть 'success' или 'failure'")
    private String testStatus;
}
