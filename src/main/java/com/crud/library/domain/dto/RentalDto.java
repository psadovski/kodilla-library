package com.crud.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Long id;
    private Long readerId;
    private Long exemplarId;
    private String rentDate;
    private String returnDate;
}
