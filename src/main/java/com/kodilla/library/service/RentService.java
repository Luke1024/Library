package com.kodilla.library.service;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Rent;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookCopyService.class);

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private UserRepository userRepository;

    public void borrowABook(RentDto rentDto) throws LibraryDatabaseException {
        boolean bookCopyFound = false;
        boolean userFound = false;
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(rentDto.getBookCopyId());
        Optional<User> user = userRepository.findById(rentDto.getUserId());
        if(bookCopy.isPresent()) {
            bookCopyFound = true;
            LOGGER.info("Book copy with id: " + bookCopy.get().getId() + " found.");
        }
        if(user.isPresent()) {
            userFound = true;
            LOGGER.info("User with id: " + user.get().getId() + " found");
        }
        if(bookCopyFound && userFound) {
            bookCopy.get().setStatus(Status.LOANED);
            bookCopyRepository.save(bookCopy.get());
            rentRepository.save(new Rent(user.get(), bookCopy.get(), LocalDate.now()));
        } else {
            LOGGER.error("User found: " + userFound + ", " + "book_copy found: " + bookCopyFound);
            throw new LibraryDatabaseException(LibraryDatabaseException.OBJECT_NOT_FOUND);
        }
    }


    public void returnABook(RentDto rentDto) throws LibraryDatabaseException {
        List<Rent> rents = rentRepository.findAll().stream()
                .filter(r -> r.getBookCopy().getId().equals(rentDto.getBookCopyId())).collect(Collectors.toList());
        if(rents.size()==1){
             Rent rent = rents.get(0);
             rent.getBookCopy().setStatus(Status.AVAILABLE);
             rent.setDateOfReturn(LocalDate.now());
        } else {
            LOGGER.error(LibraryDatabaseException.RENT_NOT_FOUND);
            throw new LibraryDatabaseException(LibraryDatabaseException.RENT_NOT_FOUND);
        }
    }
}
