package com.example.springboot_ping_app.mapper;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.entity.Ping;
import jakarta.persistence.Column;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PingMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "ipAddress", source = "ipAddress")
    @Mapping(target = "domain", source = "domain")
    @Mapping(target = "checkDate", source = "checkDate")
    @Mapping(target = "testStatus", source = "testStatus")
    @Mapping(target = "pingResult", source = "pingResult")
    PingDto toDto(Ping entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ipAddress", source = "ipAddress")
    @Mapping(target = "domain", source = "domain")
    @Mapping(target = "checkDate", source = "checkDate")
    @Mapping(target = "testStatus", source = "testStatus")
    @Mapping(target = "pingResult", source = "pingResult")
    Ping toEntity(PingDto pingDto);

    List<PingDto> modelsToDto(List<Ping> pings);

    List<Ping> dtoToModels(List<PingDto> pingDtos);
}
