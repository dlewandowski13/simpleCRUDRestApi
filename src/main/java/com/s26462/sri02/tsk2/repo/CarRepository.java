package com.s26462.sri02.tsk2.repo;


import com.s26462.sri02.tsk2.model.Car;
import com.s26462.sri02.tsk2.model.Engine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();

    @Query("from Car as c left join fetch c.engines where c.id = :carId")
    Optional<Car> getCarEngineById(@Param("carId") Long carId);
    @Query("from Car as c left join fetch c.engines where c.id = :carId")
    Optional<Car> getCarsWithEngineByCarId(@PathVariable Long carId);
}
