package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.BookCopyDto;
import com.kodilla.library.service.NotFoundException;
import org.springframework.stereotype.Service;
@Service
public class BookCopiesMapper {

    public BookCopyDto mapToBookCopiesDto(final BookCopy bookCopies) throws NotFoundException {
        return new BookCopyDto(
                bookCopies.getId(),
                bookCopies.getBook().getId(),
                bookCopies.getBook().getBookCopyList().stream()
                        .filter(b -> b.getId().equals(bookCopies.getId()))
                        .map(BookCopy::getBookStatus)
                        .findFirst()
                        .orElseThrow(NotFoundException::new)
        );
    }
}
