package com.crud.library.repository;

import com.crud.library.domain.Exemplar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {
    Long getExemplarsQuantityByTitle(String title);
    List<Exemplar> findAll();
    Optional<Exemplar> findById(Long id);
    Exemplar save(Exemplar exemplar);
}
