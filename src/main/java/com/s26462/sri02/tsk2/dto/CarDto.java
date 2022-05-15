package com.s26462.sri02.tsk2.dto;


import com.s26462.sri02.tsk2.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto extends RepresentationModel<CarDto> {
    private Long id;
    @NotBlank(message = "Marka nie może być pusta")
    private String brand;
    @NotBlank(message = "Model nie może być pusty")
    private String model;
    private int prodYear;
//    private String engine;
}
