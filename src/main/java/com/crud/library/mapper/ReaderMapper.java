package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader mapReaderDtoToReader(final ReaderDto readerDto) {
        if (readerDto == null) {
            return null;
        }
        return new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getSignUpDate());
    }

    public ReaderDto mapReaderToReaderDto(final Reader reader) {
        if (reader == null) {
            return null;
        }
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getSignUpDate());
    }

    public List<ReaderDto> mapReadersToReadersDto(final List<Reader> readerList) {
        if (readerList == null) {
            return null;
        }
        return readerList.stream()
                .map(t -> mapReaderToReaderDto(t))
                .collect(Collectors.toList());
    }
}
