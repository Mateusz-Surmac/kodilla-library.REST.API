package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private Long id;
    private Long bookCopyId;
    private BookStatus bookStatus;

    public BookCopyDto(Long bookCopyId, BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        this.bookCopyId = bookCopyId;
    }
}
