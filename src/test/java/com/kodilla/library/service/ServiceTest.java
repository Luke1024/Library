package com.kodilla.library.service;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.*;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.domain.dto.BorrowReturnDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private RentService rentService;

    private static User user = new User("Firstname", "Lastname", LocalDate.now());
    private static Title title = new Title("Title", "Author", 2019);
    private static BookCopyDto bookCopyDto = new BookCopyDto(1L,"Title", Status.AVAILABLE);

    private void addUser(){
        userService.addUser(user);
        List<User> userList = userService.getAllUsers();
        userList.stream().forEach(u -> System.out.println(u.toString()));
    }

    private void addTitle() {
        titleService.addTitle(title);
        List<Title> titleList = titleService.getAllTitles();
        titleList.stream().forEach(t -> System.out.println(t.toString()));
    }

    private void addBookCopies() throws LibraryDatabaseException {
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);
        List<BookCopy> bookCopyList = bookCopyService.getAllAvailableCopiesByTitleId(1L);
        bookCopyList.stream().forEach(b -> System.out.println(b.toString()));
    }

    private void borrowABook() throws LibraryDatabaseException {
        List<User> userList = userService.getAllUsers();
        List<Title> titleList = titleService.getAllTitles();
        rentService.borrowABook(new BorrowReturnDto(userList.get(0).getId(), titleList.get(0).getId()));
    }

    @Test
    public void libraryTest() throws LibraryDatabaseException {
        addUser();
        addTitle();
        addBookCopies();
        borrowABook();

        //clean
        //cleanDatabase();
    }
}
