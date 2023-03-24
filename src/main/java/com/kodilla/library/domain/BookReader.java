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
@Entity(name = "BOOK_READERS")
public class BookReader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BOOK_READER_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DATE_OF_ACCOUNT_CREATION")
    private LocalDate dateOfAccountCreation;

    public BookReader(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.dateOfAccountCreation = LocalDate.now();
    }

}
