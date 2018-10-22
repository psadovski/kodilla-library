package com.crud.library.mapper;

import com.crud.library.domain.Exemplar;
import com.crud.library.domain.ExemplarStatus;
import com.crud.library.domain.dto.ExemplarDto;
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
public class ExemplarMapperTestSuite {

    @InjectMocks
    private ExemplarMapper exemplarMapper;

    @Test
    public void testMapExemplarDtoToExemplarWhenExemplarDtoIsNull() {
        //Given
        ExemplarDto exemplarDto = null;
        //When
        Exemplar exemplar = exemplarMapper.mapExemplarDtoToExemplar(exemplarDto);
        //Then
        assertNull(exemplar);
    }

    @Test
    public void testMapExemplarToExemplarDtoWhenExemplarIsNull() {
        //Given
        Exemplar exemplar = null;
        //When
        ExemplarDto exemplarDto = exemplarMapper.mapExemplarToExemplarDto(exemplar);
        //Then
        assertNull(exemplarDto);
    }

    @Test
    public void testMapExemplarDtoToExemplar() {
        //Given
        ExemplarDto exemplarDto = new ExemplarDto(1L, 1L, ExemplarStatus.RENTED);
        //When
        Exemplar exemplar = exemplarMapper.mapExemplarDtoToExemplar(exemplarDto);
        //Then
        assertThat(exemplarDto).isEqualToComparingFieldByField(exemplar);
    }

    @Test
    public void testMapExemplarToExemplarDto() {
        //Given
        Exemplar exemplar = new Exemplar(1L, 1L, ExemplarStatus.RENTED);
        //When
        ExemplarDto exemplarDto = exemplarMapper.mapExemplarToExemplarDto(exemplar);
        //Then
        assertThat(exemplar).isEqualToComparingFieldByField(exemplarDto);
    }

    @Test
    public void testMapExemplarsToExemplarsDtoWhenExemplarsListIsNull() {
        //Given
        List<Exemplar> exemplars = null;
        //When
        List<ExemplarDto> exemplarsDto = exemplarMapper.mapExemplarsToExemplarsDto(exemplars);
        //Then
        assertNull(exemplarsDto);
    }

    @Test
    public void testMapExemplarsToExemplarsDto() {
        //Given
        List<Exemplar> exemplars = new ArrayList<>();
        exemplars.add(new Exemplar(1L, 1L, ExemplarStatus.RENTED));
        //When
        List<ExemplarDto> exemplarsDto = exemplarMapper.mapExemplarsToExemplarsDto(exemplars);
        //Then
        assertEquals(1, exemplarsDto.size());
        assertThat(exemplarsDto.equals(Arrays.asList(new Exemplar(1L, 1L, ExemplarStatus.RENTED)))).isTrue();
    }
}

