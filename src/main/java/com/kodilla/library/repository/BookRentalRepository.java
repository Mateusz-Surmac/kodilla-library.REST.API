package com.kodilla.library.repository;

import com.kodilla.library.domain.BookRental;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BookRentalRepository extends CrudRepository<BookRental, Long> {

    @Override
    List<BookRental> findAll();

    @Override
    BookRental save(final BookRental bookRental);

    @Query
    BookRental getByRentId(@Param("BOOK_RENT_ID") Long BOOK_RENT_ID);

}
