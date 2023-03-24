package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookReader;
import com.kodilla.library.domain.BookReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReaderMapper {

    public BookReader mapToBookReader(final BookReaderDto bookReaderDto) {
        return new BookReader(
                bookReaderDto.getName(),
                bookReaderDto.getSurname()
        );
    }
    public BookReaderDto mapToBookReaderDto(final BookReader bookReader) {
        return new BookReaderDto(
                bookReader.getName(),
                bookReader.getSurname()
        );
    }

    public List<BookReaderDto> mapToBookReaderDtoList(final List<BookReader> bookReader) {
        return bookReader.stream()
                        .map(reader -> new BookReaderDto(
                                reader.getName(),
                                reader.getSurname()
                        ))
                        .collect(Collectors.toList());
    }
}
