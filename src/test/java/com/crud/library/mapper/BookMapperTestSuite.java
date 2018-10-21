package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class BookMapperTestSuite {

    @InjectMocks
    private BookMapper bookMapper;

    @Test
    public void testMapBookDtoToBookWhenBookDtoIsNull() {
        //Given
        BookDto bookDto = null;
        //When
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        //Then
        assertNull(book);
    }

    @Test
    public void testMapBookToBookDtoWhenBookIsNull() {
        //Given
        Book book = null;
        //When
        BookDto bookDto = bookMapper.mapBookToBookDto(book);
        //Then
        assertNull(bookDto);
    }

    @Test
    public void testMapBookDtoToBook() {
        //Given
        BookDto bookDto = new BookDto(1L, "Title", "Author", "2001");
        //When
        Book book = bookMapper.mapBookDtoToBook(bookDto);
        //Then
        assertThat(bookDto).isEqualToComparingFieldByField(book);
    }

    @Test
    public void testMapBookToBookDto() {
        //Given
        Book book = new Book(1L, "Title", "Author", "2001");
        //When
        BookDto bookDto = bookMapper.mapBookToBookDto(book);
        //Then
        assertThat(book).isEqualToComparingFieldByField(bookDto);
    }
}
