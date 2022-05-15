package com.s26462.sri02.tsk2.repo;

import com.s26462.sri02.tsk2.model.Engine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface  EngineRepository extends CrudRepository<Engine, Long> {
    List<Engine> findAll();

}
