package com.kodilla.library.controller;

import com.kodilla.library.domain.dto.TitleCreationDto;
import com.kodilla.library.domain.dto.TitleDto;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.TitleService;
import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/library")
public class TitleController {
    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private TitleService titleService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = LibraryDatabaseException.class)
    public String handleLibraryDatabaseException(LibraryDatabaseException e){
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles")
    public List<TitleDto> getTitles(){
        return titleMapper.mapToTitlesDtoList(titleService.getAllTitles());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titles/{id}")
    public TitleDto getTitle(@PathVariable Long id) throws LibraryDatabaseException {
        return titleMapper.mapToTitleDto(titleService.getTitle(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/titles")
    public void addTitle(@RequestBody TitleCreationDto titleCreationDto){
        titleService.addTitle(titleMapper.mapToTitleFromCreationDto(titleCreationDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/titles/{id}")
    public void deleteTitle(@PathVariable Long id) {
        titleService.deleteTitleById(id);
    }
}
