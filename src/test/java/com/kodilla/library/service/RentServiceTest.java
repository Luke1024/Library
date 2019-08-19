package com.kodilla.library.service;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.*;
import com.kodilla.library.domain.dto.BorrowReturnDto;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.RentRepository;
import com.kodilla.library.repository.TitleRepository;
import com.kodilla.library.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RentServiceTest {

    @Autowired
    private RentService rentService;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void borrowABook_getAllRents_findRentById() throws LibraryDatabaseException {

        User user1 = new User("Firstname1", "Lastname1", LocalDate.now());
        User user2 = new User("Firstname2", "Lastname2", LocalDate.now());

        userRepository.save(user1);
        userRepository.save(user2);

        Title title1 = new Title("Title1", "Author1", 2019);
        Title title2 = new Title("Title2", "Author2", 2019);

        titleRepository.save(title1);
        titleRepository.save(title2);

        BookCopy bookCopy1 = new BookCopy(title1, Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(title2, Status.AVAILABLE);

        Long userId1 = user1.getId();
        Long userId2 = user2.getId();

        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);

        Long bookCopyId1 = bookCopy1.getId();
        Long bookCopyId2 = bookCopy2.getId();

        rentService.borrowABook(new BorrowReturnDto(userId1, bookCopyId1));
        rentService.borrowABook(new BorrowReturnDto(userId2, bookCopyId2));

        List<Rent> matchedRents = rentService.getAllRents().stream().filter(f ->
                f.getUser().getId()==userId1 && f.getBookCopy().getId()==bookCopyId1 ||
                f.getUser().getId()==userId2 && f.getBookCopy().getId()==bookCopyId2)
        .collect(Collectors.toList());

        Assert.assertTrue(matchedRents.size()>0);

        //Clean up
        matchedRents.stream().forEach(r -> rentService.deleteRentById(r.getId()));
    }

    @Test
    public void deleteRentById() throws LibraryDatabaseException {
        User user1 = new User("Firstname1", "Lastname1", LocalDate.now());

        userRepository.save(user1);

        Title title1 = new Title("Title1", "Author1", 2019);

        titleRepository.save(title1);

        BookCopy bookCopy1 = new BookCopy(title1, Status.AVAILABLE);

        Long userId1 = user1.getId();

        bookCopyRepository.save(bookCopy1);

        Long bookCopyId1 = bookCopy1.getId();

        rentService.borrowABook(new BorrowReturnDto(userId1, bookCopyId1));

        List<Rent> matchedRent = rentService.getAllRents().stream().filter(f ->
                f.getUser().getId()==userId1 && f.getBookCopy().getId()==bookCopyId1)
                .collect(Collectors.toList());

        Assert.assertTrue(matchedRent.size()==1);

        rentService.deleteRentById(matchedRent.get(0).getId());

        matchedRent = rentService.getAllRents().stream().filter(f ->
                f.getUser().getId()==userId1 && f.getBookCopy().getId()==bookCopyId1)
                .collect(Collectors.toList());

        Assert.assertTrue(matchedRent.size()==0);
    }

    @Test
    public void returnABook() throws LibraryDatabaseException {

        User user1 = new User("Firstname1", "Lastname1", LocalDate.now());

        userRepository.save(user1);

        Title title1 = new Title("Title1", "Author1", 2019);

        titleRepository.save(title1);

        BookCopy bookCopy1 = new BookCopy(title1, Status.AVAILABLE);

        Long userId1 = user1.getId();

        bookCopyRepository.save(bookCopy1);

        Long bookCopyId1 = bookCopy1.getId();

        rentService.borrowABook(new BorrowReturnDto(userId1, bookCopyId1));

        rentService.returnABook(new BorrowReturnDto(userId1, bookCopyId1));

        List<Rent> matchedRent = rentService.getAllRents().stream().filter(f ->
                f.getUser().getId()==userId1 && f.getBookCopy().getId()==bookCopyId1)
                .collect(Collectors.toList());

        Assert.assertTrue(matchedRent.get(0).getDateOfReturn()!=null);
    }
}