package com.kodilla.library.repository;

import com.kodilla.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {

    @Override
    List<BookCopy> findAll();

    Optional<BookCopy> findById(Long id);

    @Override
    BookCopy save(final BookCopy bookCopy);
}
