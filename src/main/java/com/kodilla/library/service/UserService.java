package com.kodilla.library.service;

import com.kodilla.library.domain.User;
import com.kodilla.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws LibraryDatabaseException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            LOGGER.info("User with id: " + id + " found.");
            return user.get();
        } else {
            LOGGER.error(LibraryDatabaseException.USER_NOT_FOUND);
            throw new LibraryDatabaseException(LibraryDatabaseException.USER_NOT_FOUND);
        }
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
