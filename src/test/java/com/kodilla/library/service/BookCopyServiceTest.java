package com.kodilla.library.service;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.domain.dto.BookCopyStatusChangerDto;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.TitleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookCopyServiceTest {

    @Autowired
    private BookCopyService bookCopyService;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    public void changeBookStatus() throws LibraryDatabaseException {
        Title title = new Title("Title", "Author", 2019);
        titleRepository.save(title);

        BookCopy bookCopy = new BookCopy(title, Status.AVAILABLE);

        bookCopyRepository.save(bookCopy);
        Long bookCopyId = bookCopy.getId();

        BookCopyStatusChangerDto bookCopyStatusChangerDto = new BookCopyStatusChangerDto(bookCopyId , Status.LOANED);
        bookCopyService.changeBookStatus(bookCopyStatusChangerDto);

        Optional<BookCopy> bookCopyRetrieved = bookCopyRepository.findById(bookCopyId);

        assertEquals(bookCopyRetrieved.get().getStatus(), Status.LOANED);
    }

    @Test
    public void saveBookCopyFromBookCopyDto() throws LibraryDatabaseException {
        Title title = new Title("Title", "Author", 2019);
        titleRepository.save(title);
        Long titleId = title.getId();

        BookCopyDto bookCopyDto = new BookCopyDto(titleId, Status.AVAILABLE);
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);

        List<BookCopy> bookCopyMatched = bookCopyRepository.findAll().stream()
                .filter(b -> b.getTitle().getId()==titleId)
                .collect(Collectors.toList());

        assertEquals(1,bookCopyMatched.size());
    }

    @Test
    public void getAllAvailableCopiesByTitleId() throws LibraryDatabaseException {
        Title title = new Title("Title", "Author", 2019);
        titleRepository.save(title);
        Long titleId = title.getId();

        BookCopyDto bookCopyDto1 = new BookCopyDto(titleId, Status.AVAILABLE);
        BookCopyDto bookCopyDto2 = new BookCopyDto(titleId, Status.AVAILABLE);

        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto1);
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto2);

        List<BookCopy> availableCopies = bookCopyService.getAllAvailableCopiesByTitleId(titleId);
        assertEquals(2, availableCopies.size());
    }

    @Test
    public void findBookCopyById() throws LibraryDatabaseException {
        Title title = new Title("Title", "Author", 2019);
        titleRepository.save(title);
        Long titleId = title.getId();

        BookCopyDto bookCopyDto = new BookCopyDto(titleId, Status.AVAILABLE);
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);

        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        BookCopy bookCopy = bookCopies.get(0);
        Long bookCopyId = bookCopy.getId();

        assertEquals(bookCopyId, bookCopyService.findBookCopyById(bookCopyId).getId());
    }

    @Test(expected = LibraryDatabaseException.class)
    public void deleteById() throws LibraryDatabaseException {
        Title title = new Title("Title", "Author", 2019);
        titleRepository.save(title);
        Long titleId = title.getId();

        BookCopyDto bookCopyDto = new BookCopyDto(titleId, Status.AVAILABLE);
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);

        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        BookCopy bookCopy = bookCopies.get(0);
        Long bookCopyIdToDelete = bookCopy.getId();

        bookCopyService.deleteById(bookCopyIdToDelete);

        BookCopy bookCopy1 = bookCopyService.findBookCopyById(bookCopyIdToDelete);
    }
}