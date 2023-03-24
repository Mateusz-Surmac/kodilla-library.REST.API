package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.BookReaderRepository;
import com.kodilla.library.repository.BookRentalRepository;
import com.kodilla.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LibraryDbService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCopyRepository bookCopyRepository;
    @Autowired
    BookReaderRepository bookReaderRepository;
    @Autowired
    BookRentalRepository bookRentalRepository;

    public BookReader addBookReader(final BookReader bookReader) {
        return bookReaderRepository.save(bookReader);
    }
    public Book addBook(final Book book) {
        return bookRepository.save(book);
    }
    public BookCopy addBookCopies(final Long bookId, BookStatus bookStatus) throws NotFoundException{
        return bookCopyRepository.save(new BookCopy(bookRepository.findById(bookId).orElseThrow(NotFoundException::new),bookStatus));
    }
    public void changeStatus(final Long bookCopyId, final BookStatus bookStatus) throws NotFoundException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(NotFoundException::new);
        bookCopy.setBookStatus(bookStatus);
    }
    public Long availableCopies(final Long bookId) throws NotFoundException {
        return bookRepository.findById(bookId).orElseThrow(NotFoundException::new).getBookCopyList().stream()
                .filter(b -> b.getBookStatus().equals(BookStatus.AVAILABLE))
                .count();
    }
    public BookRental borrowBookCopy(final Long bookCopyId, final Long bookReaderId) throws NotFoundException{
        BookCopy bookCopy = bookRepository.findById(bookCopyId).orElseThrow(NotFoundException::new).getBookCopyList().stream()
                .filter(b -> b.getBookStatus().equals(BookStatus.AVAILABLE))
                .findFirst().orElseThrow(NotFoundException::new);
        bookCopy.setBookStatus(BookStatus.RENTED);
        return bookRentalRepository.save(new BookRental(bookCopy,bookReaderRepository.findById(bookReaderId).orElseThrow(NotFoundException::new)));
    }
    public void returnBookCopy(final Long rentId){
        BookRental bookRental = bookRentalRepository.getByRentId(rentId);
        bookRental.getBookCopy().setBookStatus(BookStatus.AVAILABLE);
        bookRentalRepository.delete(bookRental);
    }
    /*----------------------------*/
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(final Long bookId) throws NotFoundException {
        return bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
    }
    public List<BookReader> getAllReaders() {
        return bookReaderRepository.findAll();
    }
    public BookReader getBookReaderById(final Long bookReaderId) throws NotFoundException {
        return bookReaderRepository.findById(bookReaderId).orElseThrow(NotFoundException::new);
    }
    public void deleteBookReader(final Long bookReaderId) {
        bookReaderRepository.deleteById(bookReaderId);
    }
    public void deleteBook(final Long bookId) {
        bookRepository.deleteById(bookId);
    }
    public void deleteBookCopy(final Long bookCopyId) {
        bookCopyRepository.deleteById(bookCopyId);
    }
}
