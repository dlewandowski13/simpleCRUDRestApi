package com.s26462.sri02.tsk2.rest;

import com.s26462.sri02.tsk2.dto.CarDto;
import com.s26462.sri02.tsk2.dto.CarEngineDto;
import com.s26462.sri02.tsk2.dto.mapper.CarDtoMapper;
import com.s26462.sri02.tsk2.dto.mapper.CarEngineDtoMapper;
import com.s26462.sri02.tsk2.dto.mapper.EngineDtoMapper;
import com.s26462.sri02.tsk2.model.Car;
import com.s26462.sri02.tsk2.model.Engine;
import com.s26462.sri02.tsk2.repo.CarRepository;
import com.s26462.sri02.tsk2.repo.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;
    private final CarEngineDtoMapper carEngineDtoMapper;
    private final CreateLinks createLinks;

    @GetMapping
    public ResponseEntity<Collection<CarDto>> getCars(){
        List<Car> allCars = carRepository.findAll();
        List<CarDto> result = allCars.stream()
                .map(carDtoMapper::convertToDto)
                .collect(Collectors.toList());
        for(CarDto dto : result) {
            dto.add(createLinks.getCarsLink());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent()) {
            CarDto carDto = carDtoMapper.convertToDto(car.get());
            Link linkSefl = createLinks.getCarByIdLink(carId);
            carDto.add(linkSefl);
            return new ResponseEntity<>(carDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{carId}/engines")
    public ResponseEntity<CarEngineDto> getCarEnginesById(@PathVariable Long carId){
        Optional<Car> car = carRepository.getCarsWithEngineByCarId(carId);
        if(car.isPresent()) {
            CarEngineDto carEngineDto = carEngineDtoMapper.convertToDtoDetails(car.get());
            Link linkSefl = createLinks.getCarEnginesByIdLink(carId);
            carEngineDto.add(linkSefl);
            return new ResponseEntity<>(carEngineDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity saveNewCar (@Valid @RequestBody CarDto car) {
        Car entity = carDtoMapper.convertToEntity(car);
        carRepository.save(entity);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PostMapping("/engines")
    public ResponseEntity saveNewCarWithDetails (@Valid @RequestBody CarEngineDto car) {
        Car entityCar = carEngineDtoMapper.convertToEntityCar(car);
        carRepository.save(entityCar);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(entityCar.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{carId}")
    public ResponseEntity updateCar(@Valid @PathVariable Long carId, @RequestBody CarDto carDto){
        Optional<Car> currentCar = carRepository.findById(carId);
        if(currentCar.isPresent()) {
            carDto.setId(carId);
            Car entity = carDtoMapper.convertToEntity(carDto);
            carRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity deleteCar(@PathVariable Long carId) {
        carRepository.deleteById(carId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
