package com.crud.library.mapper;

import com.crud.library.domain.Rental;
import com.crud.library.domain.dto.RentalDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RentalMapperTestSuite {

    @InjectMocks
    private RentalMapper rentalMapper;

    @Test
    public void testMapRentalDtoToRentalWhenRentalDtoIsNull() {
        //Given
        RentalDto rentalDto = null;
        //When
        Rental rental = rentalMapper.mapRentalDtoToRental(rentalDto);
        //Then
        assertNull(rental);
    }

    @Test
    public void testMapRentalToRentalDtoWhenRentalIsNull() {
        //Given
        Rental rental = null;
        //When
        RentalDto rentalDto = rentalMapper.mapRentalToRentalDto(rental);
        //Then
        assertNull(rentalDto);
    }

    @Test
    public void testMapRentalDtoToRental() {
        //Given
        RentalDto rentalDto = new RentalDto(1L, 1L, 1L, "12.03.2018", "15.03.2018");
        //When
        Rental rental = rentalMapper.mapRentalDtoToRental(rentalDto);
        //Then
        assertThat(rentalDto).isEqualToComparingFieldByField(rental);
    }

    @Test
    public void testMapRentalToRentalDto() {
        //Given
        Rental rental = new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018");
        //When
        RentalDto rentalDto = rentalMapper.mapRentalToRentalDto(rental);
        //Then
        assertThat(rental).isEqualToComparingFieldByField(rentalDto);
    }

    @Test
    public void testMapRentalsToRentalsDtoWhenRentalsListIsNull() {
        //Given
        List<Rental> rentals = null;
        //When
        List<RentalDto> rentalsDto = rentalMapper.mapRentalsToRentalsDto(rentals);
        //Then
        assertNull(rentalsDto);
    }

    @Test
    public void testMapRentalsToRentalsDto() {
        //Given
        List<Rental> rentals= new ArrayList<>();
        rentals.add(new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018"));
        //When
        List<RentalDto> rentalsDto = rentalMapper.mapRentalsToRentalsDto(rentals);
        //Then
        assertEquals(1, rentalsDto.size());
        assertThat(rentalsDto.equals(Arrays.asList(new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018")))).isTrue();
    }
}

