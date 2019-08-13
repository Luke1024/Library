package com.kodilla.library.controller;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.domain.dto.BookCopyStatusChangerDto;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class BookCopyController {

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Autowired
    private BookCopyService bookCopyService;

    @RequestMapping(method = RequestMethod.GET, value = "/copies/{titleId}")
    public List<BookCopyDto> getAvailableCopiesByTitleId(@PathVariable Long titleId) throws LibraryDatabaseException {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyService.getAllAvailableCopiesByTitleId(titleId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copies")
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) throws LibraryDatabaseException {
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/copies/status")
    public void changeBookCopyStatus(@RequestBody BookCopyStatusChangerDto bookCopyStatusChangerDto) throws LibraryDatabaseException {
        bookCopyService.changeBookStatus(bookCopyStatusChangerDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/copies")
    public void deleteBookCopy(@RequestParam Long id) {
        bookCopyService.deleteById(id);
    }
}
