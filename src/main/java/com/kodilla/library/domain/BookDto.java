package com.kodilla.library.domain;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String publicationDate;
    private List<BookCopy> bookCopyDtoList;

    public BookDto(String title, String author, String publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }
}
