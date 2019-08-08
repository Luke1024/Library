package com.kodilla.library.service;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.dto.BookCopyStatusChangerDto;
import com.kodilla.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public void changeBookStatus(BookCopyStatusChangerDto bookCopyStatusChangerDto){
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyStatusChangerDto.getId());
        if(bookCopy != null) {
            bookCopy.setStatus(bookCopyStatusChangerDto.getStatus());
            bookCopyRepository.save(bookCopy);
        }
        //if not available?
    }

    public void saveBookCopy(BookCopy bookCopy){
        bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> getAllAvailableCopiesByTitleId(Long id){
        return bookCopyRepository.findBookCopiesByTitleId(id).stream()
                .filter(bookCopy -> bookCopy.getStatus()== Status.AVAILABLE)
                .collect(Collectors.toList());
    }

    public BookCopy findBookCopyById(Long id){
        return bookCopyRepository.findById(id);
    }
}
