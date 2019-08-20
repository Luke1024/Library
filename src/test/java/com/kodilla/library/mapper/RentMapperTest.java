package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import com.kodilla.library.domain.dto.RentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentMapperTest {

    @Autowired
    private RentMapper rentMapper;

    @Test
    public void mapToDtoRentDtoList() {
        LocalDate localDate = LocalDate.now();
        User user = new User(1L, "Firstname", "Lastname", localDate);
        Title title = new Title(1L, "Title", "Author", 2019);
        BookCopy bookCopy = new BookCopy(1L,title, Status.AVAILABLE);
        List<Rent> rents = new ArrayList<>(Arrays.asList(new Rent(1L,user, bookCopy, localDate, localDate)));
        List<RentDto> rentDtos = new ArrayList<>(Arrays.asList(new RentDto(1L, 1L, "Firstname",
                "Lastname", 1L, "Title", localDate, localDate)));

        assertThat(rentDtos, sameBeanAs(rentMapper.mapToDtoRentDtoList(rents)));
    }

    @Test
    public void mapToRentDto() {
        LocalDate localDate = LocalDate.now();
        User user = new User(1L, "Firstname", "Lastname", localDate);
        Title title = new Title(1L, "Title", "Author", 2019);
        BookCopy bookCopy = new BookCopy(1L,title, Status.AVAILABLE);
        Rent rent = new Rent(1L ,user, bookCopy, localDate, localDate);
        RentDto rentDto = new RentDto(1L, 1L, "Firstname",
                "Lastname", 1L, "Title", localDate, localDate);

        assertThat(rentDto, sameBeanAs(rentMapper.mapToRentDto(rent)));
    }
}