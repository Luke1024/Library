package com.kodilla.library.service;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TitleServiceTest {

    @Autowired
    private TitleService titleService;

    @Test
    public void getAllTitles() throws LibraryDatabaseException {
        Title title1 = new Title("Title1", "Author1", 2019);
        titleService.addTitle(title1);
        Title title2 = new Title("Title2", "Author2", 2019);
        titleService.addTitle(title2);

        List<Title> titles = titleService.getAllTitles();

        List<Title> titlesFiltered = titles.stream()
                .filter(t -> t.getId()==title1.getId() || t.getId()==title2.getId()).collect(Collectors.toList());

        assertThat(title1, sameBeanAs(titlesFiltered.get(0)));
        assertThat(title2, sameBeanAs(titlesFiltered.get(1)));

        //clean up
        titleService.deleteTitleById(title1.getId());
        titleService.deleteTitleById(title2.getId());
    }

    @Test
    public void getTitle_addTitle() throws LibraryDatabaseException {
        Title title1 = new Title("Title1", "Author1", 2019);
        titleService.addTitle(title1);

        assertThat(title1, sameBeanAs(titleService.getTitle(title1.getId())));

        //clean up
        titleService.deleteTitleById(title1.getId());
    }

    @Test(expected = LibraryDatabaseException.class)
    public void deleteTitleById() throws LibraryDatabaseException {
        Title title1 = new Title("Title1", "Author1", 2019);
        titleService.addTitle(title1);

        Long id = title1.getId();

        assertThat(title1, sameBeanAs(titleService.getTitle(id)));

        titleService.deleteTitleById(id);

        titleService.deleteTitleById(id);
    }
}