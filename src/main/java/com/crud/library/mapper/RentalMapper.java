package com.crud.library.mapper;

import com.crud.library.domain.Rental;
import com.crud.library.domain.dto.RentalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

    public List<RentalDto> mapRentalsToRentalsDto(final List<Rental> rentalList) {
        if (rentalList == null) {
            return null;
        }
        return rentalList.stream()
                .map(t -> new RentalDto(t.getId(), t.getReaderId(), t.getExemplarId(), t.getRentDate(), t.getReturnDate()))
                .collect(Collectors.toList());
    }

    public Rental mapRentalDtoToRental(final RentalDto rentalDto) {
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

    public RentalDto mapRentalToRentalDto(final Rental rental) {
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
