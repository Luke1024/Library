package com.kodilla.library.mapper;

import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleCreationDto;
import com.kodilla.library.domain.dto.TitleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleMapperTest {

    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void mapToTitlesDtoList() {
        List<Title> titleList = new ArrayList<>(Arrays.asList(new Title(1L, "Title", "Author", 2019)));
        List<TitleDto> titleDtos = new ArrayList<>(Arrays.asList(new TitleDto(1L, "Title", "Author", 2019)));

        assertThat(titleDtos , sameBeanAs(titleMapper.mapToTitlesDtoList(titleList)));
    }

    @Test
    public void mapToTitleFromCreationDto() {
        TitleCreationDto titleCreationDto = new TitleCreationDto("Title", "Author", 2019);
        Title title = new Title("Title", "Author", 2019);

        assertThat(title, sameBeanAs(titleMapper.mapToTitleFromCreationDto(titleCreationDto)));
    }
}