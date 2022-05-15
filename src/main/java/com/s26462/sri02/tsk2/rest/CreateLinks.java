package com.s26462.sri02.tsk2.rest;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CreateLinks {

    public Link getCarsLink(){
        Link linkSefl = linkTo(methodOn(CarController.class).getCars()).withSelfRel();
        return linkSefl;
    }
    public Link getCarByIdLink(Long carId){
        Link linkSefl = linkTo(methodOn(CarController.class).getCarById(carId)).withSelfRel();
        return linkSefl;
    }

    public Link getCarEnginesByIdLink(Long carId){
        Link linkSefl = linkTo(methodOn(CarController.class).getCarEnginesById(carId)).withSelfRel();
        return linkSefl;
    }

    public Link getEnginesLink() {
        Link linkSefl = linkTo(methodOn(EngineControler.class).getEngines()).withSelfRel();
        return linkSefl;
    }

    public Link getEngineByIdLink(Long engineId) {
        Link linkSefl = linkTo(methodOn(EngineControler.class).getEngineById(engineId)).withSelfRel();
        return linkSefl;
    }
}
