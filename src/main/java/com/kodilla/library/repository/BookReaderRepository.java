package com.kodilla.library.repository;

import com.kodilla.library.domain.BookReader;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookReaderRepository extends CrudRepository<BookReader, Long> {

    @Override
    List<BookReader> findAll();

    Optional<BookReader> findById(Long id);

    @Override
    BookReader save(final BookReader bookReader);

}
