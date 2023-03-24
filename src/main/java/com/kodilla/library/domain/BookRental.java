package com.kodilla.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "BOOK_RENTAL")
public class BookRental {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENT_ID")
    private Long rentId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_READER_ID")
    private BookReader bookReader;

    @Column(name = "DATE_OF_RENT")
    private LocalDate dateOfRent;

    @Column(name = "DATE_OF_RETURN")
    private LocalDate dateOfReturn;

    public BookRental(BookCopy bookCopy, BookReader bookReader) {
        this.bookCopy = bookCopy;
        this.bookReader = bookReader;
        this.dateOfRent = LocalDate.now();
        this.dateOfReturn = LocalDate.now().plusMonths(1);
    }
}
