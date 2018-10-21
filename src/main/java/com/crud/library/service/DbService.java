package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Exemplar;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.ExemplarRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public Exemplar saveExemplar(final Exemplar exemplar) {
        return exemplarRepository.save(exemplar);
    }

    public Long getExemplarQuantityByTitle(String title) {
        return exemplarRepository.getExemplarsQuantityByTitle(title);
    }

    public List<Exemplar> getAllExeplars() {
        return exemplarRepository.findAll();
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Book> getBook(final Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Reader> getReader(final Long id) {
        return readerRepository.findById(id);
    }

    public Optional<Exemplar> getExemplar(final Long id) {
        return exemplarRepository.findById(id);
    }

    public Optional<Rental> getRental(final Long id) {
        return rentalRepository.findById(id);
    }

    @Transactional
    public Exemplar updateStatus(final Long id, final Exemplar exemplar) {
        if (getExemplar(id).isPresent()) {
            Exemplar updatedExemplar = getExemplar(id).get();
            updatedExemplar.setStatus(exemplar.getStatus());
            updatedExemplar.setBookId(exemplar.getBookId());

            return exemplarRepository.save(updatedExemplar);
        }
        return null;
    }

    @Transactional
    public Rental borrowExemplar(Long id, final Rental rental) {
        if (getExemplar(id).isPresent()) {
            Rental updatedRental = getRental(id).get();
            updatedRental.setRentDate(rental.getRentDate());

            return rentalRepository.save(updatedRental);
        }
        return null;
    }

    @Transactional
    public Rental returnExemplar(Long id, final Rental rental) {
        if (rentalRepository.existsById(id)) {
            Rental theReturn = getRental(id).get();
            theReturn.setReturnDate(rental.getReturnDate());

            return rentalRepository.save(theReturn);
        }
        return null;
    }
}
