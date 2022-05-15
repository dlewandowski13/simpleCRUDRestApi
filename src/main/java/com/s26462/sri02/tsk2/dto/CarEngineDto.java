package com.s26462.sri02.tsk2.dto;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEngineDto extends RepresentationModel<CarEngineDto> {
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    private int prodYear;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<EngineDto> engines;
}
