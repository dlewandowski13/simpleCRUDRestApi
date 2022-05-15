package com.s26462.sri02.tsk2.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    private int prodYear;

    @OneToMany(targetEntity = Engine.class, cascade = CascadeType.ALL)
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "car_Id", referencedColumnName = "id")
    private Set<Engine> engines;

}
