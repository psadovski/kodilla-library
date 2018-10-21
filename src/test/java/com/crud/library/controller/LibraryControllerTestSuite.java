package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.Exemplar;
import com.crud.library.domain.ExemplarStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.ExemplarDto;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.domain.dto.RentalDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.ExemplarMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.mapper.RentalMapper;
import com.crud.library.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    private RentalMapper rentalMapper = new RentalMapper();
    private ReaderMapper readerMapper = new ReaderMapper();
    private BookMapper bookMapper = new BookMapper();
    private ExemplarMapper exemplarMapper = new ExemplarMapper();

    @Test
    public void testCreateBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, "Title", "Author", "2001");
        Book book = bookMapper.mapBookDtoToBook(bookDto);

        when(dbService.saveBook(any())).thenReturn(book);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(post("/v1/library/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, times(1)).saveBook(any());
    }

    @Test
    public void testCreateReader() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto(1L, "Name", "Surname", "21.10.2008");
        Reader reader = readerMapper.mapReaderDtoToReader(readerDto);

        when(dbService.saveReader(any())).thenReturn(reader);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(readerDto);

        //When & Then
        mockMvc.perform(post("/v1/library/createReader")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, times(1)).saveReader(any());
    }

    @Test
    public void testCreateExemplar() throws Exception {
        //Given
        ExemplarDto exemplarDto = new ExemplarDto(1L, 1L, ExemplarStatus.RENTED);
        Exemplar exemplar = exemplarMapper.mapExemplarDtoToExemplar(exemplarDto);

        when(dbService.saveExemplar(any())).thenReturn(exemplar);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(exemplarDto);

        //When & Then
        mockMvc.perform(post("/v1/library/createExemplar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
        verify(dbService, times(1)).saveExemplar(any());
    }

    @Test
    public void testGetExemplars() throws Exception {
        //Given
        List<Exemplar> exemplars = new ArrayList<>();
        exemplars.add(new Exemplar(1L, 1L, ExemplarStatus.RENTED));

        when(dbService.getAllExeplars()).thenReturn(exemplars);

        //When & Then
        mockMvc.perform(get("/v1/library/getExemplars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[0].status", is(ExemplarStatus.RENTED)));
    }

    @Test
    public void testGetReaders() throws Exception {
        //Given
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader(1L, "Name", "Surname", "21.10.2008"));

        when(dbService.getAllReaders()).thenReturn(readers);

        //When & Then
        mockMvc.perform(get("/v1/library/getReaders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Name")))
                .andExpect(jsonPath("$[0].surname", is("Surname")))
                .andExpect(jsonPath("$[0].signUpDate", is("21.10.2008")));
    }

    @Test
    public void testGetRentals() throws Exception {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018"));

        when(dbService.getAllRentals()).thenReturn(rentals);

        //When & Then
        mockMvc.perform(get("/v1/library/getRentals")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].readerId", is(1)))
                .andExpect(jsonPath("$[0].exemplarId", is(1)))
                .andExpect(jsonPath("$[0].rentDate", is("12.03.2018")))
                .andExpect(jsonPath("$[0].returnDate", is("15.03.2018")));
    }

    @Test
    public void testGetBook() throws Exception {
        //Given
        Book book = new Book(1L, "Title", "Author", "2001");

        when(dbService.getBook(1L)).thenReturn(Optional.of(book));

        //When & Then
        mockMvc.perform(get("/v1/library/getBook")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.author", is("Author")))
                .andExpect(jsonPath("$.publicationYear", is("2001")));
    }

    @Test
    public void testGetReader() throws Exception {
        //Given
        Reader reader = new Reader(1L, "Name", "Surname", "21.10.2008");

        when(dbService.getReader(1L)).thenReturn(Optional.of(reader));

        //When & Then
        mockMvc.perform(get("/v1/library/getReader")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Name")))
                .andExpect(jsonPath("$.surname", is("Surname")))
                .andExpect(jsonPath("$.signUpDate", is("21.10.2008")));
    }

    @Test
    public void testGetExemplar() throws Exception {
        //Given
        Exemplar exemplar = new Exemplar(1L, 1L, ExemplarStatus.RENTED);

        when(dbService.getExemplar(1L)).thenReturn(Optional.of(exemplar));

        //When & Then
        mockMvc.perform(get("/v1/library/getExemplar")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.bookId", is(1)))
                .andExpect(jsonPath("$.status", is(ExemplarStatus.RENTED)));
    }

    @Test
    public void testGetRental() throws Exception {
        //Given
        Rental rental = new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018");

        when(dbService.getRental(1L)).thenReturn(Optional.of(rental));

        //When & Then
        mockMvc.perform(get("/v1/library/getRental")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.readerId", is(1)))
                .andExpect(jsonPath("$.exemplarId", is(1)))
                .andExpect(jsonPath("$.rentDate", is("12.03.2018")))
                .andExpect(jsonPath("$.returnDate", is("15.03.2018")));
    }

    @Test
    public void testUpdateStatus() throws Exception {
        //Given
        ExemplarDto exemplarDto = new ExemplarDto(1L, 1L, ExemplarStatus.AVAILABLE);
        Exemplar exemplar = exemplarMapper.mapExemplarDtoToExemplar(exemplarDto);

        when(dbService.updateStatus(eq(exemplarDto.getId()), any())).thenReturn(exemplar);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(exemplarDto);

        //When & Then
        mockMvc.perform(put("/v1/library/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.bookId").value(1L))
                .andExpect(jsonPath("$.status").value(ExemplarStatus.AVAILABLE));
    }

    @Test
    public void testBorrowExemplar() throws Exception {
        //Given
        RentalDto rentalDto = new RentalDto(1L, 1L, 1L, "12.03.2018", null);
        Rental rental = rentalMapper.mapRentalDtoToRental(rentalDto);

        when(dbService.borrowExemplar(eq(rentalDto.getId()), any())).thenReturn(rental);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentalDto);

        //When & Then
        mockMvc.perform(put("/v1/library/borrow")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.readerId").value(1L))
                .andExpect(jsonPath("$.exemplarId").value(1L))
                .andExpect(jsonPath("$.rentDate").value("12.03.2018"))
                .andExpect(jsonPath("$.returnDate").value(null));
    }

    @Test
    public void testReturnExemplar() throws Exception {
        //Given
        RentalDto rentalDto = new RentalDto(1L, 1L, 1L, "12.03.2018", "15.03.2018");
        Rental rental = rentalMapper.mapRentalDtoToRental(rentalDto);

        when(dbService.returnExemplar(eq(rentalDto.getId()), any())).thenReturn(rental);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentalDto);

        //When & Then
        mockMvc.perform(put("/v1/library/return")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.readerId").value(1L))
                .andExpect(jsonPath("$.exemplarId").value(1L))
                .andExpect(jsonPath("$.rentDate").value("12.03.2018"))
                .andExpect(jsonPath("$.returnDate").value("15.03.2018"));
    }
}
