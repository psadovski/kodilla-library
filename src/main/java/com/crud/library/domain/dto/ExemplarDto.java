package com.crud.library.domain.dto;

import com.crud.library.domain.ExemplarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExemplarDto {
    private Long id;
    private Long bookId;
    private ExemplarStatus status;
}
