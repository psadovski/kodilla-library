package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.Exemplar;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.ExemplarDto;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.domain.dto.RentalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        if (readerDto == null) {
            return null;
        }
        return new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getSignUpDate());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        if (reader == null) {
            return null;
        }
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getSignUpDate());
    }

    public Book mapToBook(final BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear());
    }

    public BookDto mapToBookDto(final Book book) {
        if (book == null) {
            return null;
        }
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear());
    }

    public Exemplar mapToExemplar(final ExemplarDto exemplarDto) {
        if (exemplarDto == null) {
            return null;
        }
        return new Exemplar(
                exemplarDto.getId(),
                exemplarDto.getBookId(),
                exemplarDto.getStatus());
    }

    public ExemplarDto mapToExemplarDto(final Exemplar exemplar) {
        if (exemplar == null) {
            return null;
        }
        return new ExemplarDto(
                exemplar.getId(),
                exemplar.getBookId(),
                exemplar.getStatus());
    }

    public List<ExemplarDto> mapToExemplarDtoList(final List<Exemplar> exemplarList) {
        if (exemplarList == null) {
            return null;
        }
        return exemplarList.stream()
                .map(t -> new ExemplarDto(t.getId(), t.getBookId(), t.getStatus()))
                .collect(Collectors.toList());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        if (readerList == null) {
            return null;
        }
        return readerList.stream()
                .map(t -> new ReaderDto(t.getId(), t.getName(), t.getSurname(), t.getSignUpDate()))
                .collect(Collectors.toList());
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        if (rentalList == null) {
            return null;
        }
        return rentalList.stream()
                .map(t -> new RentalDto(t.getId(), t.getReaderId(), t.getExemplarId(), t.getRentDate(), t.getReturnDate()))
                .collect(Collectors.toList());
    }

    public Rental mapToRental(final RentalDto rentalDto) {
        if (rentalDto == null) {
            return null;
        }
        return new Rental(
                rentalDto.getId(),
                rentalDto.getReaderId(),
                rentalDto.getExemplarId(),
                rentalDto.getRentDate(),
                rentalDto.getReturnDate());
    }

    public RentalDto mapToRentalDto(final Rental rental) {
        if (rental == null) {
            return null;
        }
        return new RentalDto(
                rental.getId(),
                rental.getReaderId(),
                rental.getExemplarId(),
                rental.getRentDate(),
                rental.getReturnDate());
    }
}
