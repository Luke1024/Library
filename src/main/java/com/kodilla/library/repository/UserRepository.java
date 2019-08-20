package com.kodilla.library.repository;

import com.kodilla.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);

    @Override
    long count();
}
