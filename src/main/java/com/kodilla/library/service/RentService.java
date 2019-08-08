package com.kodilla.library.service;

import com.kodilla.library.controller.BookCopyNotFoundException;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private BookCopyService bookCopyService;

    public void borrowABook(RentDto rentDto) throws BookCopyNotFoundException {
        BookCopy bookCopy = bookCopyService.findBookCopyById(rentDto.getBookCopyId());
        if(isBookCopyAvailable(bookCopy)) {
            saveRent(rentDto);
            changeBookCopyStatusToLoaned(bookCopy);
        } else {
            throw new BookCopyNotFoundException();
        }
        //if not available?
    }

    private boolean isBookCopyAvailable(BookCopy bookCopy){
        return bookCopy.getStatus() == Status.AVAILABLE;
    }

    private void saveRent(RentDto rentDto){
        rentRepository.save(rentMapper.mapBorrow(rentDto));
    }

    private void changeBookCopyStatusToLoaned(BookCopy bookCopy){
        bookCopy.setStatus(Status.LOANED);
        bookCopyService.saveBookCopy(bookCopy);
    }



    public void returnABook(RentDto rentDto){
        BookCopy bookCopy = bookCopyService.findBookCopyById(rentDto.getBookCopyId());
        if(checkIfBookOutsideLibrary(bookCopy)) {
            addReturnDateToRent(rentDto);
            bookCopy.setStatus(Status.AVAILABLE);
            bookCopyService.saveBookCopy(bookCopy);
        }

        //if not?
    }

    private boolean checkIfBookOutsideLibrary(BookCopy bookCopy) {
        Status status = bookCopy.getStatus();
        return status == Status.LOANED || status == Status.LOST;
    }

    private void addReturnDateToRent(RentDto rentDto){
         rentRepository.findById(rentDto.getBookCopyId());
    }
}
