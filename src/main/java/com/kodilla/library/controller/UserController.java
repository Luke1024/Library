package com.kodilla.library.controller;

import com.kodilla.library.domain.dto.UserCreationDto;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = LibraryDatabaseException.class)
    public String handleLibraryDatabaseException(LibraryDatabaseException e){
        return e.getMessage();
    }

    @GetMapping(value = "/users")
    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping(value = "/users/{id}")
    public UserDto getUser(@PathVariable Long id) throws LibraryDatabaseException {
        return userMapper.mapToUserDto(userService.getUserById(id));
    }

    @PostMapping(value = "/users")
    public void addUser(@RequestBody UserCreationDto userCreationDto){
        userService.addUser(userMapper.mapToUserFromCreationDto(userCreationDto));
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
