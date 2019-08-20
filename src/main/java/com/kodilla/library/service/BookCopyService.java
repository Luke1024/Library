package com.kodilla.library.service;
import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.domain.dto.BookCopyStatusChangerDto;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.TitleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookCopyService.class);

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private TitleRepository titleRepository;

    public void changeBookStatus(BookCopyStatusChangerDto bookCopyStatusChangerDto) throws LibraryDatabaseException {
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(bookCopyStatusChangerDto.getId());
        if(bookCopy.isPresent()) {
            bookCopy.get().setStatus(bookCopyStatusChangerDto.getStatus());
            bookCopyRepository.save(bookCopy.get());
        } else {
            LOGGER.error(LibraryDatabaseException.BOOK_COPY_NOT_FOUND);
            throw new LibraryDatabaseException(LibraryDatabaseException.BOOK_COPY_NOT_FOUND);
        }
    }

    public void saveBookCopyFromBookCopyDto(BookCopyDto bookCopyDto) throws LibraryDatabaseException {
        Optional<Title> title = titleRepository.findById(bookCopyDto.getTitleId());
        if (title.isPresent()) {
            BookCopy bookCopy = new BookCopy(title.get(), bookCopyDto.getStatus());
            title.get().getBookCopies().add(bookCopy);
            bookCopyRepository.save(bookCopy);
        } else {
            LOGGER.error(LibraryDatabaseException.TITLE_NOT_FOUND);
            throw new LibraryDatabaseException(LibraryDatabaseException.TITLE_NOT_FOUND);
        }
    }

    public List<BookCopy> getAllAvailableCopiesByTitleId(Long id) throws LibraryDatabaseException {
        Optional<Title> title = titleRepository.findById(id);
        Title titleWithCopies;
        if(title.isPresent()) {
            titleWithCopies = title.get();
        } else {
            LOGGER.error(LibraryDatabaseException.TITLE_NOT_FOUND);
            throw new LibraryDatabaseException(LibraryDatabaseException.TITLE_NOT_FOUND);
        }
        return titleWithCopies.getBookCopies();
    }

    public BookCopy findBookCopyById(Long id) throws LibraryDatabaseException {
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(id);
        if(bookCopy.isPresent()) {
            return bookCopy.get();
        } else {
            LOGGER.error(LibraryDatabaseException.BOOK_COPY_NOT_FOUND);
            throw new LibraryDatabaseException((LibraryDatabaseException.BOOK_COPY_NOT_FOUND));
        }
    }

    public void deleteById(Long id) {
        bookCopyRepository.deleteById(id);
    }
}
