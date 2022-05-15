package com.s26462.sri02.tsk2.rest;

import com.s26462.sri02.tsk2.dto.CarDto;
import com.s26462.sri02.tsk2.dto.EngineCarDto;
import com.s26462.sri02.tsk2.dto.EngineDto;
import com.s26462.sri02.tsk2.dto.mapper.CarDtoMapper;
import com.s26462.sri02.tsk2.dto.mapper.EngineDtoMapper;
import com.s26462.sri02.tsk2.model.Car;
import com.s26462.sri02.tsk2.model.Engine;
import com.s26462.sri02.tsk2.repo.CarRepository;
import com.s26462.sri02.tsk2.repo.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/engines")
@RequiredArgsConstructor
public class EngineControler {
    private final EngineRepository engineRepository;
    private final EngineDtoMapper engineDtoMapper;
    private final CarDtoMapper carDtoMapper;
    private final CarRepository carRepository;
    private final CreateLinks createLinks;

    @GetMapping
    public ResponseEntity<Collection<EngineDto>> getEngines(){
        List<Engine> allEngines = engineRepository.findAll();
        List<EngineDto> result = allEngines.stream()
                .map(engineDtoMapper::convertToDto)
                .collect(Collectors.toList());
        for(EngineDto dto : result) {
            dto.add(createLinks.getEnginesLink());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{engineId}")
    public ResponseEntity<EngineDto> getEngineById(@PathVariable Long engineId){
        Optional<Engine> engine = engineRepository.findById(engineId);
        if(engine.isPresent()){
            EngineDto engineDto = engineDtoMapper.convertToDto(engine.get());
            Link linkSefl = createLinks.getEngineByIdLink(engineId);
            engineDto.add(linkSefl);
            return new ResponseEntity<>(engineDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity saveNewEngine (@Valid @RequestBody EngineDto engine) {
        Engine entity = engineDtoMapper.convertToEntity(engine);
        engineRepository.save(entity);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{engineId}")
    public ResponseEntity updateEngine(@Valid @PathVariable Long engineId, @RequestBody EngineDto engineDto){
        Optional<Engine> currentEngine = engineRepository.findById(engineId);
        if(currentEngine.isPresent()){
            engineDto.setId(engineId);
            Engine entity = engineDtoMapper.convertToEntity(engineDto);
            engineRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{engineId}/car/{carId}")
    public ResponseEntity updateEngineCar(@Valid @PathVariable Long engineId, @PathVariable Long carId){
        Optional<Engine> currentEngine = engineRepository.findById(engineId);
        Optional<Car> currentCar = carRepository.findById(carId);
        if(currentEngine.isPresent() && currentCar.isPresent()){
            CarDto carDto = carDtoMapper.convertToDto(currentCar.get());
            Car car = carDtoMapper.convertToEntity(carDto);
            EngineCarDto engineDto = engineDtoMapper.convertEngineCarToDto(currentEngine.get());
            engineDto.setCar(car);
            Engine entity = engineDtoMapper.convertEngineCarToEntity(engineDto);
            engineRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{engineId}/car/{carId}")
    public ResponseEntity deleteEngineCar(@Valid @PathVariable Long engineId, @PathVariable Long carId){
        Optional<Engine> currentEngine = engineRepository.findById(engineId);
        Optional<Car> currentCar = carRepository.findById(carId);
        if(currentEngine.isPresent() && currentCar.isPresent()){
            EngineCarDto engineDto = engineDtoMapper.convertEngineCarToDto(currentEngine.get());
            engineDto.setCar(null);
            Engine entity = engineDtoMapper.convertEngineCarToEntity(engineDto);
            engineRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{engineId}")
    public ResponseEntity deleteEngine(@PathVariable Long engineId) {
        engineRepository.deleteById(engineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
