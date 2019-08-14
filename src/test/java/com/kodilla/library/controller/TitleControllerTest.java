package com.kodilla.library.controller;

import com.google.gson.Gson;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.TitleCreationDto;
import com.kodilla.library.domain.dto.TitleDto;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.TitleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(TitleController.class)
public class TitleControllerTest {

    @MockBean
    private TitleService titleService;

    @MockBean
    private TitleMapper titleMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTitles() throws Exception {
        List<Title> titles = new ArrayList<>(Arrays.asList(new Title("Title", "Author", 2019)));
        List<TitleDto> titleDtos = new ArrayList<>(Arrays.asList(new TitleDto(1L, "Title", "Author", 2019)));

        when(titleService.getAllTitles()).thenReturn(titles);
        when(titleMapper.mapToTitlesDtoList(ArgumentMatchers.any())).thenReturn(titleDtos);

        mockMvc.perform(get("/library/titles")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titleId", is(1)))
                .andExpect(jsonPath("$[0].title", is("Title")))
                .andExpect(jsonPath("$[0].author", is("Author")))
                .andExpect(jsonPath("$[0].publicationYear", is(2019)));
    }

    @Test
    public void getTitle() throws Exception {
        Title title = new Title("Title", "Author", 2019);
        TitleDto titleDto = new TitleDto(1L, "Title", "Author", 2019);

        when(titleMapper.mapToTitleDto(ArgumentMatchers.any())).thenReturn(titleDto);

        mockMvc.perform(get("/library/titles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titleId", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.author", is("Author")))
                .andExpect(jsonPath("$.publicationYear", is(2019)));
    }

    @Test
    public void addTitle() throws Exception {
        TitleCreationDto titleCreationDto = new TitleCreationDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(titleCreationDto);

        mockMvc.perform(post("/library/titles")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTitle() throws Exception {
        mockMvc.perform(delete("/library/titles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}