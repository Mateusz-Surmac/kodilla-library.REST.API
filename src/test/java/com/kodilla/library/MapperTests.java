package com.kodilla.library;

import com.kodilla.library.domain.*;
import com.kodilla.library.mapper.BookCopiesMapper;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.BookReaderMapper;
import com.kodilla.library.mapper.BookRentalMapper;
import com.kodilla.library.service.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MapperTests {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCopiesMapper bookCopiesMapper;
    @Autowired
    private BookReaderMapper bookReaderMapper;
    @Autowired
    private BookRentalMapper bookRentalMapper;

    @Test
    void mapToBookDtoTest() {
        //Given
        Book book = new Book("Book", "Author", "2000");

        //When
        BookDto bookDto = bookMapper.mapToBookDto(book);

        //Then
        assertNotNull(bookDto);
        assertEquals("Book", bookDto.getTitle());
        assertEquals("Author", bookDto.getAuthor());
        assertEquals("2000", bookDto.getPublicationDate());
    }

    @Test
    void mapToBookTest() {
        //Given
        BookDto bookDto = new BookDto("BookDto","AuthorDto","2000Dto");

        //When
        Book book = bookMapper.mapToBook(bookDto);

        //Then
        assertNotNull(book);
        assertEquals("BookDto", book.getTitle());
        assertEquals("AuthorDto", book.getAuthor());
        assertEquals("2000Dto", book.getPublicationDate());
    }

    @Test
    void mapToBookDtoListTest() {
        //Given
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Book1", "Author1", "2000.1"));
        bookList.add(new Book("Book2", "Author2", "2000.2"));

        //When
        List<BookDto> bookDtoList = bookMapper.mapToBookDtoList(bookList);

        //Then
        assertEquals("Book1", bookDtoList.get(0).getTitle());
        assertEquals("Book2", bookDtoList.get(1).getTitle());
    }
    @Test
    void mapToBookReaderDtoTest() {
        //Given
        BookReader bookReader = new BookReader("Name","Surname");

        //When
        BookReaderDto bookReaderDto = bookReaderMapper.mapToBookReaderDto(bookReader);

        //Then
        assertNotNull(bookReaderDto);
        assertEquals("Name", bookReaderDto.getName());
        assertEquals("Surname", bookReaderDto.getSurname());
    }
    @Test
    void mapToBookReaderTest() {
        //Given
        BookReaderDto bookReaderDto = new BookReaderDto("NameDto","SurnameDto");

        //When
        BookReader bookReader = bookReaderMapper.mapToBookReader(bookReaderDto);

        //Then
        assertNotNull(bookReaderDto);
        assertEquals("NameDto", bookReader.getName());
        assertEquals("SurnameDto", bookReader.getSurname());
    }
    @Test
    void mapToBookReaderDtoListTest() {
        //Given
        List<BookReader> bookReaderList = new ArrayList<>();
        bookReaderList.add(new BookReader("Name1","Surname1"));
        bookReaderList.add(new BookReader("Name2", "Surname2"));

        //When
        List<BookReaderDto> bookReaderDtoList = bookReaderMapper.mapToBookReaderDtoList(bookReaderList);

        //Then
        assertEquals("Name1", bookReaderDtoList.get(0).getName());
        assertEquals("Name2", bookReaderDtoList.get(1).getName());
    }
    @Test
    void mapToBookCopiesDtoTest(){
        //Given
        Book book = new Book("Book", "Author", "2000");
        BookCopy bookCopy = new BookCopy(book,BookStatus.AVAILABLE);

        try {
            //When
            BookCopyDto bookCopyDto = bookCopiesMapper.mapToBookCopiesDto(bookCopy);

            //Then
            assertNotNull(bookCopyDto);
            assertEquals(BookStatus.AVAILABLE,bookCopyDto.getBookStatus());
        } catch (NotFoundException e) {
        }
    }
    @Test
    void mapToBookRentalDtoTest() {
        //Given
        Book book = new Book("Book", "Author", "2000");
        BookReader bookReader = new BookReader("Name","Surname");
        BookCopy bookCopy = new BookCopy(book,BookStatus.AVAILABLE);
        BookRental bookRental = new BookRental(bookCopy,bookReader);

        //When
        BookRentalDto bookRentalDto = bookRentalMapper.mapToBookRentalDto(bookRental);

        //Then
        assertNotNull(bookRentalDto);
        assertEquals(BookStatus.AVAILABLE,bookRentalDto.getBookCopy().getBookStatus());
        assertEquals("Book", bookRentalDto.getBookCopy().getBook().getTitle());


    }
}
