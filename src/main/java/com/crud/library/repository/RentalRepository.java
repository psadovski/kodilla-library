package com.crud.library.repository;

import com.crud.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends CrudRepository<Rental, Long> {

    List<Rental> findAll();
    Optional<Rental> findById(Long id);
    boolean existsById(Long id);
}
