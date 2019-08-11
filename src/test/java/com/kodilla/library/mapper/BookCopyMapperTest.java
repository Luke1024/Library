package com.kodilla.library.mapper;

import com.kodilla.library.controller.LibraryController;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.BookCopyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyMapperTest {

    @Autowired
    private BookCopyMapper bookCopyMapper;

    @Test
    public void mapToBookCopyDtoList() {
        List<BookCopy> bookCopyList = new ArrayList<>(
                Arrays.asList(new BookCopy(1L, new Title(1L, "Title", "Author", 2019), Status.AVAILABLE)));
        List<BookCopyDto> bookCopyDtos = new ArrayList<>(Arrays.asList(new BookCopyDto(1L, "Title", Status.AVAILABLE)));

        assertThat(bookCopyDtos, sameBeanAs(bookCopyMapper.mapToBookCopyDtoList(bookCopyList)));
    }
}