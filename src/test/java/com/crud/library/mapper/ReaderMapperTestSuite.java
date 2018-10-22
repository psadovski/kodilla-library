package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.dto.ReaderDto;
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
public class ReaderMapperTestSuite {

    @InjectMocks
    private ReaderMapper readerMapper;

    @Test
    public void testMapReaderDtoToReaderWhenReaderDtoIsNull() {
        //Given
        ReaderDto readerDto = null;
        //When
        Reader reader = readerMapper.mapReaderDtoToReader(readerDto);
        //Then
        assertNull(reader);
    }

    @Test
    public void testMapReaderToReaderDtoWhenReaderIsNull() {
        //Given
        Reader reader = null;
        //When
        ReaderDto readerDto = readerMapper.mapReaderToReaderDto(reader);
        //Then
        assertNull(readerDto);
    }

    @Test
    public void testMapReaderDtoToReader() {
        //Given
        ReaderDto readerDto = new ReaderDto(1L, "Name", "Surname", "21.10.2008");
        //When
        Reader reader = readerMapper.mapReaderDtoToReader(readerDto);
        //Then
        assertThat(readerDto).isEqualToComparingFieldByField(reader);
    }

    @Test
    public void testMapReaderToReaderDto() {
        //Given
        Reader reader = new Reader(1L, "Name", "Surname", "21.10.2008");
        //When
        ReaderDto readerDto = readerMapper.mapReaderToReaderDto(reader);
        //Then
        assertThat(reader).isEqualToComparingFieldByField(readerDto);
    }

    @Test
    public void testMapReadersToReadersDtoWhenReadersListIsNull() {
        //Given
        List<Reader> readers = null;
        //When
        List<ReaderDto> readersDto = readerMapper.mapReadersToReadersDto(readers);
        //Then
        assertNull(readersDto);
    }

    @Test
    public void testMapExemplarsToExemplarsDto() {
        //Given
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader(1L, "Name", "Surname", "21.10.2008"));
        //When
        List<ReaderDto> readersDto = readerMapper.mapReadersToReadersDto(readers);
        //Then
        assertEquals(1, readersDto.size());
        assertThat(readersDto.equals(Arrays.asList(new Reader(1L, "Name", "Surname", "21.10.2008")))).isTrue();
    }
}
