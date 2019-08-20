package com.kodilla.library.controller;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.domain.dto.BookCopyStatusChangerDto;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.service.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = LibraryDatabaseException.class)
    public String handleLibraryDatabaseException(LibraryDatabaseException e){
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/title/{titleId}")
    public List<BookCopyDto> getAvailableCopiesByTitleId(@PathVariable Long titleId) throws LibraryDatabaseException {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyService.getAllAvailableCopiesByTitleId(titleId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/{id}")
    public BookCopyDto getCopyByTitleId(@PathVariable Long id) throws LibraryDatabaseException {
        return bookCopyMapper.mapToBookCopyDto(bookCopyService.findBookCopyById(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copies")
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) throws LibraryDatabaseException {
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/copies/status")
    public void changeBookCopyStatus(@RequestBody BookCopyStatusChangerDto bookCopyStatusChangerDto) throws LibraryDatabaseException {
        bookCopyService.changeBookStatus(bookCopyStatusChangerDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/copies/{id}")
    public void deleteBookCopy(@PathVariable Long id) {
        bookCopyService.deleteById(id);
    }
}
