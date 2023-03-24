package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "BOOK_COPIES")
public class BookCopy {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "BOOK_COPY_ID",unique = true)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "BOOK_STATUS")
    private BookStatus bookStatus;

    public BookCopy(Book book, BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        this.book = book;
    }

}
