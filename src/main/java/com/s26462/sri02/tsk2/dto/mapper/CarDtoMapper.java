package com.s26462.sri02.tsk2.dto.mapper;

import com.s26462.sri02.tsk2.dto.CarDto;
import com.s26462.sri02.tsk2.dto.CarEngineDto;
import com.s26462.sri02.tsk2.model.Car;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarDtoMapper {
    private final ModelMapper modelMapper;

    public CarDto convertToDto(Car car) {

        return modelMapper.map(car, CarDto.class);
    }

    public Car convertToEntity(CarDto dto) {

        return modelMapper.map(dto, Car.class);
    }
}
