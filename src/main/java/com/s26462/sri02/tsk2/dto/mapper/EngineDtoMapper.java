package com.s26462.sri02.tsk2.dto.mapper;

import com.s26462.sri02.tsk2.dto.CarEngineDto;
import com.s26462.sri02.tsk2.dto.EngineCarDto;
import com.s26462.sri02.tsk2.dto.EngineDto;
import com.s26462.sri02.tsk2.model.Engine;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
public class EngineDtoMapper {
    private final ModelMapper modelMapper;



    public EngineDto convertToDto(Engine e) {return modelMapper.map(e, EngineDto.class);}
    public Engine convertToEntity(EngineDto dto) {return modelMapper.map(dto, Engine.class);}

    public EngineCarDto convertEngineCarToDto(Engine e) {return modelMapper.map(e, EngineCarDto.class);}
    public Engine convertEngineCarToEntity(EngineCarDto dto) {return modelMapper.map(dto, Engine.class);}
}
