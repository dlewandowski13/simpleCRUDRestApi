package com.s26462.sri02.tsk2.dto.mapper;

import com.s26462.sri02.tsk2.dto.CarDto;
import com.s26462.sri02.tsk2.dto.CarEngineDto;
import com.s26462.sri02.tsk2.model.Car;
import com.s26462.sri02.tsk2.model.Engine;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarEngineDtoMapper {
    private final ModelMapper modelMapper;

    public CarEngineDto convertToDtoDetails(Car car) {

        return modelMapper.map(car, CarEngineDto.class);
    }

    public Car convertToEntityCar(CarEngineDto dto) {

        return modelMapper.map(dto, Car.class);
    }
    public Engine convertToEntityEngine(CarEngineDto dto) {

        return modelMapper.map(dto, Engine.class);
    }
}
