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

    @Override
    BookCopy save(BookCopy bookCopy);

    Optional<BookCopy> findById(Long id);

    void deleteById(Long id);

    //List<BookCopy> findBookCopiesByTitleId(Long titleId);

    @Override
    long count();
}
