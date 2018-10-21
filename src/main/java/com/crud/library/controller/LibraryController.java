package com.crud.library.controller;

import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.ExemplarDto;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.domain.dto.RentalDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.ExemplarMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.mapper.RentalMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    private DbService dbService;

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ExemplarMapper exemplarMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = APPLICATION_JSON_VALUE)
    public Long createBook(@RequestBody BookDto bookDto) {
        return dbService.saveBook(bookMapper.mapBookDtoToBook(bookDto)).getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public Long createReader(@RequestBody ReaderDto readerDto) {
        return dbService.saveReader(readerMapper.mapReaderDtoToReader(readerDto)).getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createExemplar", consumes = APPLICATION_JSON_VALUE)
    public Long createExemplar(@RequestBody ExemplarDto exemplarDto) {
        return dbService.saveExemplar(exemplarMapper.mapExemplarDtoToExemplar(exemplarDto)).getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getNumber")
    public Long getExemplarQuantityByTitle(@RequestParam String title) {
        return dbService.getExemplarQuantityByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getExemplars")
    public List<ExemplarDto> getExemplars() {
        return exemplarMapper.mapExemplarsToExemplarsDto(dbService.getAllExeplars());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapReadersToReadersDto(dbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentals")
    public List<RentalDto> getRentals() {
        return rentalMapper.mapRentalsToRentalsDto(dbService.getAllRentals());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long id) {
        return bookMapper.mapBookToBookDto(dbService.getBook(id).orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam Long id) {
        return readerMapper.mapReaderToReaderDto(dbService.getReader(id).orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getExemplar")
    public ExemplarDto getExemplar(@RequestParam Long id) {
        return exemplarMapper.mapExemplarToExemplarDto(dbService.getExemplar(id).orElse(null));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRental")
    public RentalDto getRental(@RequestParam Long id) {
        return rentalMapper.mapRentalToRentalDto(dbService.getRental(id).orElse(null));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update")
    public ExemplarDto updateStatus(@RequestBody ExemplarDto exemplarDto) {
        return exemplarMapper.mapExemplarToExemplarDto(dbService.updateStatus(exemplarDto.getId(),
                exemplarMapper.mapExemplarDtoToExemplar(exemplarDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "borrow")
    public RentalDto borrowExemplar(@RequestBody RentalDto rentalDto) {
        return rentalMapper.mapRentalToRentalDto(dbService.borrowExemplar(rentalDto.getId(),
                rentalMapper.mapRentalDtoToRental(rentalDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "return")
    public RentalDto returnExemplar(@RequestBody RentalDto rentalDto) {
        return rentalMapper.mapRentalToRentalDto(dbService.returnExemplar(rentalDto.getId(),
                rentalMapper.mapRentalDtoToRental(rentalDto)));
    }
}
