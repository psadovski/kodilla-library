package com.crud.library.controller;

import com.crud.library.domain.dto.BookDto;
import com.crud.library.domain.dto.ExemplarDto;
import com.crud.library.domain.dto.ReaderDto;
import com.crud.library.domain.dto.RentalDto;
import com.crud.library.mapper.LibraryMapper;
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
    private LibraryMapper libraryMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createBook", consumes = APPLICATION_JSON_VALUE)
    public Long createBook(@RequestBody BookDto bookDto) {
        return dbService.saveBook(libraryMapper.mapToBook(bookDto)).getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public Long createReader(@RequestBody ReaderDto readerDto) {
        return dbService.saveReader(libraryMapper.mapToReader(readerDto)).getId();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createExemplar", consumes = APPLICATION_JSON_VALUE)
    public Long createExemplar(@RequestBody ExemplarDto exemplarDto) {
        return dbService.saveExemplar(libraryMapper.mapToExemplar(exemplarDto)).getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getNumber")
    public Long getExemplarQuantityByTitle(@RequestParam String title) {
        return dbService.getExemplarQuantityByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getExemplars")
    public List<ExemplarDto> getTasks() {
        return libraryMapper.mapToExemplarDtoList(dbService.getAllExeplars());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return libraryMapper.mapToReaderDtoList(dbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentals")
    public List<RentalDto> getRentals() {
        return libraryMapper.mapToRentalDtoList(dbService.getAllRentals());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long id) throws BookNotFoundException {
        return libraryMapper.mapToBookDto(dbService.getBook(id).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam Long id) throws ReaderNotFoundException {
        return libraryMapper.mapToReaderDto(dbService.getReader(id).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getExemplar")
    public ExemplarDto getExemplar(@RequestParam Long id) throws ExemplarNotFoundException {
        return libraryMapper.mapToExemplarDto(dbService.getExemplar(id).orElseThrow(ExemplarNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRental")
    public RentalDto getRental(@RequestParam Long id) throws RentalNotFoundException {
        return libraryMapper.mapToRentalDto(dbService.getRental(id).orElseThrow(RentalNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update")
    public ExemplarDto updateStatus(@RequestBody ExemplarDto exemplarDto) {
        return libraryMapper.mapToExemplarDto(dbService.updateStatus(exemplarDto.getId(),
                libraryMapper.mapToExemplar(exemplarDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "borrow")
    public RentalDto borrowExemplar(@RequestBody RentalDto rentalDto) {
        return libraryMapper.mapToRentalDto(dbService.borrowExemplar(rentalDto.getId(),
                libraryMapper.mapToRental(rentalDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "return")
    public RentalDto returnExemplar(@RequestBody RentalDto rentalDto) {
        return libraryMapper.mapToRentalDto(dbService.returnExemplar(rentalDto.getId(),
                libraryMapper.mapToRental(rentalDto)));
    }
}
