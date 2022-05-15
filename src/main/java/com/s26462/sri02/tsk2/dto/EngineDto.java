package com.s26462.sri02.tsk2.dto;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineDto extends RepresentationModel<EngineDto> {

    private Long id;
    @NotBlank(message = "Należy podać rodzaj silnika (np. Benzynowy)")
    private String engineType;
    @NotBlank(message = "Należy podać pojemność silnika w cm3")
    @Size(min=2, max=4, message = "Akceptowane wartości pomiędzy 2, a 4 znaki")
    private String engineCapacity;
    @NotBlank(message = "Należy podać moc silnika w KM")
    private String enginePower;
//    private Car car;
}
