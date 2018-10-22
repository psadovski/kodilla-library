package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends CrudRepository<Reader, Long> {

    List<Reader> findAll();
    Optional<Reader> findById(Long id);
    Reader save(Reader reader);
}
