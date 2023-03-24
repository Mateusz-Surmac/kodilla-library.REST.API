package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookRental;
import com.kodilla.library.domain.BookRentalDto;
import org.springframework.stereotype.Service;

@Service
public class BookRentalMapper {
    public BookRentalDto mapToBookRentalDto(BookRental bookRental) {
        return new BookRentalDto(
                bookRental.getRentId(),
                bookRental.getBookCopy(),
                bookRental.getBookReader(),
                bookRental.getDateOfRent(),
                bookRental.getDateOfReturn());
    }
}
