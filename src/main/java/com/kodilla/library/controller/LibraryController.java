package com.kodilla.library.controller;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.dto.*;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.BookCopyService;
import com.kodilla.library.service.RentService;
import com.kodilla.library.service.TitleService;
import com.kodilla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private BookCopyMapper bookCopyMapper;
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private TitleService titleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RentService rentService;

    //Get Type
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles")
    public List<TitleDto> getTitles(){
        return titleMapper.mapToTitlesDtoList(titleService.getAllTitles());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copies/{titleId}")
    public List<BookCopyDto> getAvailableCopiesByTitleId(@PathVariable Long titleId) throws LibraryDatabaseException {
        return bookCopyMapper.mapToBookCopyDtoList(bookCopyService.getAllAvailableCopiesByTitleId(titleId));
    }

    //Post Type
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody UserCreationDto userCreationDto){
        userService.addUser(userMapper.mapToUserFromCreationDto(userCreationDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/titles")
    public void addTitle(@RequestBody TitleCreationDto titleCreationDto){
        titleService.addTitle(titleMapper.mapToTitleFromCreationDto(titleCreationDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copies")
    public void addBookCopy(@RequestBody BookCopyDto bookCopyDto) throws LibraryDatabaseException {
        bookCopyService.saveBookCopyFromBookCopyDto(bookCopyDto);
    }

    //Put type
    @RequestMapping(method = RequestMethod.PUT, value = "/copies/status")
    public void changeBookCopyStatus(@RequestBody BookCopyStatusChangerDto bookCopyStatusChangerDto) throws LibraryDatabaseException {
        bookCopyService.changeBookStatus(bookCopyStatusChangerDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rent/borrow")
    public void borrowABook(@RequestBody RentDto rentDto) throws LibraryDatabaseException {
        rentService.borrowABook(rentDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rent/return")
    public void returnCopy(@RequestBody RentDto rentDto) throws LibraryDatabaseException {
        rentService.returnABook(rentDto);
    }
}
