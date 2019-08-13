package com.kodilla.library.service;

import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.TitleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import java.util.List;
import java.util.Optional;

@Service
public class TitleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TitleService.class);

    @Autowired
    private TitleRepository titleRepository;

    public List<Title> getAllTitles(){
        return titleRepository.findAll();
    }

    public Title getTitle(Long id) throws LibraryDatabaseException {
        Optional<Title> title = titleRepository.findById(id);
        if(title.isPresent()) {
            LOGGER.info("Title with id: " + id + " found");
            return title.get();
        } else {
            LOGGER.error(LibraryDatabaseException.TITLE_NOT_FOUND);
            throw new LibraryDatabaseException(LibraryDatabaseException.TITLE_NOT_FOUND);
        }
    }

    public void addTitle(Title title){
        titleRepository.save(title);
    }

    public void deleteTitleById(Long id){
        titleRepository.deleteById(id);
    }
}
