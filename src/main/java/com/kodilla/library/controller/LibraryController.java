package com.kodilla.library.controller;


import com.kodilla.library.domain.*;
import com.kodilla.library.domain.DtoX.AvailableCopiesDto;
import com.kodilla.library.domain.DtoX.BookRentDto;
import com.kodilla.library.domain.DtoX.ChangeBookStatusDto;
import com.kodilla.library.domain.DtoX.ReturnBookDto;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.BookReaderMapper;
import com.kodilla.library.service.NotFoundException;
import com.kodilla.library.service.LibraryDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {
    @Autowired
    private LibraryDbService libraryDbService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookReaderMapper bookReaderMapper;

    @PostMapping(value = "addBookReader", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookReader> addBookReader(@RequestBody BookReaderDto bookReaderDto) {
        return ResponseEntity.ok(libraryDbService.addBookReader(bookReaderMapper.mapToBookReader(bookReaderDto)));
    }

    @PostMapping(value = "addBook",  consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(libraryDbService.addBook(bookMapper.mapToBook(bookDto)));
    }
    @PostMapping(value = "addBookCopies", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopy> addBookCopies(@RequestBody BookCopyDto bookCopiesDto) throws NotFoundException{
        return ResponseEntity.ok(libraryDbService.addBookCopies(bookCopiesDto.getBookCopyId(),bookCopiesDto.getBookStatus()));
    }
    @PutMapping(value = "changeStatus",  consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changeStatus(@RequestBody ChangeBookStatusDto changeBookStatusDto) throws NotFoundException{
        libraryDbService.changeStatus(changeBookStatusDto.getBookCopyId(),changeBookStatusDto.getBookStatus());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "availableCopies", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> availableCopies(@RequestBody AvailableCopiesDto availableCopiesDto) throws NotFoundException {
        return ResponseEntity.ok(libraryDbService.availableCopies(availableCopiesDto.getBookId()));
    }


    @PostMapping(value = "borrowBookCopy", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRental> borrowBookCopy(@RequestBody BookRentDto bookRentDto) throws NotFoundException{
        return ResponseEntity.ok(libraryDbService.borrowBookCopy(bookRentDto.getBookCopyId(),bookRentDto.getBookReaderId()));
    }
    @DeleteMapping(value = "returnBookCopy", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> returnBookCopy(@RequestBody ReturnBookDto returnBookDto) {
        libraryDbService.returnBookCopy(returnBookDto.getId());
        return ResponseEntity.ok().build();
    }
    /*---------------------------------------------*/
    @GetMapping(value = "getBooks")
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(libraryDbService.getAllBooks()));
    }
    @GetMapping(value = "getBook")
    public ResponseEntity<BookDto> getBook(@RequestParam Long bookId) throws NotFoundException {
        return ResponseEntity.ok(bookMapper.mapToBookDto(libraryDbService.getBookById(bookId)));
    }
    @GetMapping(value = "getReaders")
    public ResponseEntity<List<BookReaderDto>> getReaders() {
        return ResponseEntity.ok(bookReaderMapper.mapToBookReaderDtoList(libraryDbService.getAllReaders()));
    }
    @GetMapping(value = "getReader")
    public ResponseEntity<BookReaderDto> getReader(@RequestParam Long bookReaderId) throws NotFoundException{
        return ResponseEntity.ok(bookReaderMapper.mapToBookReaderDto(libraryDbService.getBookReaderById(bookReaderId)));
    }
    @DeleteMapping(value = "deleteReader")
    public ResponseEntity<Void> deleteReader(@RequestParam Long bookReaderId){
        libraryDbService.deleteBookReader(bookReaderId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "deleteBook")
    public ResponseEntity<Void> deletebook(@RequestParam Long bookId) {
        libraryDbService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(value = "deleteBookCopy")
    public ResponseEntity<Void> deleteBookCopy(@RequestParam Long bookCopyId) {
        libraryDbService.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }
}
