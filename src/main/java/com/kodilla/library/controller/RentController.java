package com.kodilla.library.controller;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.dto.BorrowReturnDto;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private RentMapper rentMapper;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = LibraryDatabaseException.class)
    public String handleLibraryDatabaseException(LibraryDatabaseException e){
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rent")
    public List<RentDto> getAllRents(){
        return rentMapper.mapToDtoRentDtoList(rentService.getAllRents());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rent/{id}")
    public RentDto getRent(@PathVariable Long id) throws LibraryDatabaseException {
        return rentMapper.mapToRentDto(rentService.findRentById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rent/borrow")
    public void borrowABook(@RequestBody BorrowReturnDto borrowReturnDto) throws LibraryDatabaseException {
        rentService.borrowABook(borrowReturnDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rent/return")
    public void returnCopy(@RequestBody BorrowReturnDto borrowReturnDto) throws LibraryDatabaseException {
        rentService.returnABook(borrowReturnDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/rent/{id}")
    public void deleteRent(@PathVariable Long id){
        rentService.deleteRentById(id);
    }
}
