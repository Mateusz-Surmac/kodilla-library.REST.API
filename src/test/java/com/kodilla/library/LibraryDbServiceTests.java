package com.kodilla.library;

import com.kodilla.library.domain.*;
import com.kodilla.library.service.LibraryDbService;
import com.kodilla.library.service.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibraryDbServiceTests {
    @Autowired
    private LibraryDbService libraryDbService;

    @Test
    void addAndDeleteBookReaderTest() {
        //Given
        BookReader bookReader = new BookReader("Name","Surname");
        int amountOfReaders = libraryDbService.getAllReaders().size();

        //When1
        libraryDbService.addBookReader(bookReader);
        Long bookReaderId = bookReader.getId();

        //Then1
        assertEquals(bookReaderId,libraryDbService.getAllReaders().get(amountOfReaders).getId());

        //When2
        int bookReadersSize = libraryDbService.getAllReaders().size();
        libraryDbService.deleteBookReader(bookReaderId);

        //Then2
        assertNotEquals(bookReadersSize,libraryDbService.getAllReaders().size());
    }
    @Test
    void addAndDeleteBookTest(){
        //Given
        Book book = new Book("Title","Author","2111-11-11");

        //When1
        libraryDbService.addBook(book);
        Long bookId = book.getId();

        try {
            //Then1
            assertEquals(book.getId(), libraryDbService.getBookById(bookId).getId());

            //When2
            int booksSize = libraryDbService.getAllBooks().size();
            libraryDbService.deleteBook(bookId);

            //Then2
            assertNotEquals(booksSize, libraryDbService.getAllBooks().size());
        } catch (NotFoundException e) {
        }
    }
    @Test
    void changeBookStatusTest() {
        //Given
        BookCopy bookcopy = libraryDbService.getAllBooks().get(0).getBookCopyList().get(0);
        BookStatus bookStatus = bookcopy.getBookStatus();
        Long bookCopyId = bookcopy.getId();

        try {
            //When
            libraryDbService.changeStatus(bookCopyId, BookStatus.DESTROYED);

            //Then
            assertNotEquals(bookStatus,libraryDbService.getAllBooks().get(0).getBookCopyList().get(0).getBookStatus());

            //Clean up
            libraryDbService.changeStatus(bookCopyId,bookStatus);

        } catch (NotFoundException e) {
        }
    }
    @Test
    void availableCopiesTest() {
        //Given
        Book book = libraryDbService.getAllBooks().get(0);

        try {
            //Then
            Long availableBookCopies = libraryDbService.availableCopies(book.getId());
            libraryDbService.addBookCopies(book.getId(), BookStatus.AVAILABLE);
            int bookListSize = book.getBookCopyList().size() - 1;
            Long bookCopyId = book.getBookCopyList().get(bookListSize).getId();

            //Then
            assertNotEquals(availableBookCopies, libraryDbService.availableCopies(book.getId()));

            //Clean up
            libraryDbService.deleteBookCopy(bookCopyId);
        } catch (NotFoundException e) {
        }
    }
}
