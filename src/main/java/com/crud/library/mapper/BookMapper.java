package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapBookDtoToBook(final BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublicationYear());
    }

    public BookDto mapBookToBookDto(final Book book) {
        if (book == null) {
            return null;
        }
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear());
    }
}
