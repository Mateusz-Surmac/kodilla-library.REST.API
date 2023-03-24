package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRentalDto {
    private Long id;
    private BookCopy bookCopy;
    private BookReader bookReader;
    private LocalDate dateOfRent;
    private LocalDate dateOfReturn;

    public BookRentalDto(BookCopy bookCopy, BookReader bookReader) {
        this.bookCopy = bookCopy;
        this.bookReader = bookReader;
        this.dateOfRent = LocalDate.now();
        this.dateOfReturn = LocalDate.now().plusMonths(1);
    }
}