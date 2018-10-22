package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Exemplar;
import com.crud.library.domain.ExemplarStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.ExemplarRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ExemplarRepository exemplarRepository;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private RentalRepository rentalRepository;

    @Test
    public void testSaveBook() {
        //Given
        Book book = new Book(1L, "Title", "Author", "2001");
        //When
        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = dbService.saveBook(book);
        //Then
        assertThat(book).isEqualToComparingFieldByField(savedBook);
    }

    @Test
    public void testSaveReader() {
        //Given
        Reader reader = new Reader(1L, "Name", "Surname", "21.10.2008");
        //When
        when(readerRepository.save(reader)).thenReturn(reader);
        Reader savedReader = dbService.saveReader(reader);
        //Then
        assertThat(reader).isEqualToComparingFieldByField(savedReader);
    }

    @Test
    public void testSaveExemplar() {
        //Given
        Exemplar exemplar = new Exemplar(1L, 1L, ExemplarStatus.RENTED);
        //When
        when(exemplarRepository.save(exemplar)).thenReturn(exemplar);
        Exemplar savedExemplar = dbService.saveExemplar(exemplar);
        //Then
        assertThat(exemplar).isEqualToComparingFieldByField(savedExemplar );
    }

    @Test
    public void testGetExemplarQuantityByTitle() {
        //Given & When
        when(exemplarRepository.getExemplarsQuantityByTitle("Buszujący w zbożu")).thenReturn(3L);
        Long quantity = exemplarRepository.getExemplarsQuantityByTitle("Buszujący w zbożu");
        //Then
        assertEquals(Optional.of(3L), Optional.of(quantity));
    }

    @Test
    public void testGetAllExemplars() {
        //Given
        List<Exemplar> exemplars = new ArrayList<>();
        exemplars.add(new Exemplar(1L, 1L, ExemplarStatus.RENTED));
        when(exemplarRepository.findAll()).thenReturn(exemplars);
        //When
        List<Exemplar> fetchedExemplarList = dbService.getAllExeplars();
        //Then
        assertNotNull(fetchedExemplarList);
        assertEquals(1, fetchedExemplarList.size());
        fetchedExemplarList.forEach(exemplarList -> {
            assertEquals((Long)1L, exemplarList.getId());
            assertEquals((Long)1L, exemplarList.getBookId());
            assertEquals(ExemplarStatus.RENTED, exemplarList.getStatus());
        });
    }

    @Test
    public void testGetAllReaders() {
        //Given
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader(1L, "Name", "Surname", "21.10.2008"));
        when(readerRepository.findAll()).thenReturn(readers);
        //When
        List<Reader> fetchedReaderList = dbService.getAllReaders();
        //Then
        assertNotNull(fetchedReaderList);
        assertEquals(1, fetchedReaderList.size());
        fetchedReaderList.forEach(readerList -> {
            assertEquals((Long)1L, readerList.getId());
            assertEquals("Name", readerList.getName());
            assertEquals("Surname", readerList.getSurname());
            assertEquals("21.10.2008", readerList.getSignUpDate());
        });
    }

    @Test
    public void testGetAllRentals() {
        //Given
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018"));
        when(rentalRepository.findAll()).thenReturn(rentals);
        //When
        List<Rental> fetchedRentalList = dbService.getAllRentals();
        //Then
        assertNotNull(fetchedRentalList);
        assertEquals(1, fetchedRentalList.size());
        fetchedRentalList.forEach(renatlList -> {
            assertEquals((Long)1L, renatlList.getId());
            assertEquals((Long)1L, renatlList.getReaderId());
            assertEquals((Long)1L, renatlList.getExemplarId());
            assertEquals("12.03.2018", renatlList.getRentDate());
            assertEquals("15.03.2018", renatlList.getReturnDate());
        });
    }

    @Test
    public void testGetBook() {
        //Given
        Book book = new Book(1L, "Title", "Author", "2001");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        //When
        Optional<Book> bookById = dbService.getBook(1L);
        //Then
        assertThat(Optional.of(book)).isEqualToComparingFieldByField(bookById);
    }

    @Test
    public void testGetReader() {
        //Given
        Reader reader = new Reader(1L, "Name", "Surname", "21.10.2008");
        when(readerRepository.findById(1L)).thenReturn(Optional.of(reader));
        //When
        Optional<Reader> readerById = dbService.getReader(1L);
        //Then
        assertThat(Optional.of(reader)).isEqualToComparingFieldByField(readerById);
    }

    @Test
    public void testGetExemplar() {
        //Given
        Exemplar exemplar = new Exemplar(1L, 1L, ExemplarStatus.RENTED);
        when(exemplarRepository.findById(1L)).thenReturn(Optional.of(exemplar));
        //When
        Optional<Exemplar> exemplarById = dbService.getExemplar(1L);
        //Then
        assertThat(Optional.of(exemplar)).isEqualToComparingFieldByField(exemplarById);
    }

    @Test
    public void testGetRental() {
        //Given
        Rental rental = new Rental(1L, 1L, 1L, "12.03.2018", "15.03.2018");
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(rental));
        //When
        Optional<Rental> rentalById = dbService.getRental(1L);
        //Then
        assertThat(Optional.of(rental)).isEqualToComparingFieldByField(rentalById);
    }
}
