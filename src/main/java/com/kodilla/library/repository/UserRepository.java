package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import javafx.concurrent.Task;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    User save(User user);

    List<User> findById(Long id);

    List<User> deleteById(Long id);

    @Override
    long count();
}
