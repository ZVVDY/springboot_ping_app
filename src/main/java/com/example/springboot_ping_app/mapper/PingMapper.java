package com.example.springboot_ping_app.mapper;

import com.example.springboot_ping_app.dto.PingDto;
import com.example.springboot_ping_app.entity.Ping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PingMapper {
    PingDto toDto(Ping entity);

    Ping toEntity(PingDto pingDto);

    List<PingDto> modelsToDto(List<Ping> users);

    List<Ping> dtoToModels(List<PingDto> usersDto);
}
