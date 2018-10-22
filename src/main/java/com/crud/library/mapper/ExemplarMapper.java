package com.crud.library.mapper;

import com.crud.library.domain.Exemplar;
import com.crud.library.domain.dto.ExemplarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExemplarMapper {

    public Exemplar mapExemplarDtoToExemplar(final ExemplarDto exemplarDto) {
        if (exemplarDto == null) {
            return null;
        }
        return new Exemplar(
                exemplarDto.getId(),
                exemplarDto.getBookId(),
                exemplarDto.getStatus());
    }

    public ExemplarDto mapExemplarToExemplarDto(final Exemplar exemplar) {
        if (exemplar == null) {
            return null;
        }
        return new ExemplarDto(
                exemplar.getId(),
                exemplar.getBookId(),
                exemplar.getStatus());
    }

    public List<ExemplarDto> mapExemplarsToExemplarsDto(final List<Exemplar> exemplarList) {
        if (exemplarList == null) {
            return null;
        }
        return exemplarList.stream()
                .map(t -> mapExemplarToExemplarDto(t))
                .collect(Collectors.toList());
    }
}
