package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReaderDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfAccountCreation;
    public BookReaderDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.dateOfAccountCreation = LocalDate.now();
    }
}
