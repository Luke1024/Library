package com.kodilla.library.controller;

import com.google.gson.Gson;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.dto.BookCopyDto;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.service.BookCopyService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookCopyController.class)
public class BookCopyControllerTest {
    @MockBean
    private BookCopyMapper bookCopyMapper;

    @MockBean
    private BookCopyService bookCopyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAvailableCopiesByTitleId() throws Exception {
        List<BookCopy> bookCopies = new ArrayList<>(
                Arrays.asList(new BookCopy(new Title("Title", "Author", 2019), Status.AVAILABLE)));
        List<BookCopyDto> bookCopiesDtos = new ArrayList<>(
                Arrays.asList(new BookCopyDto(1L, "Title", Status.AVAILABLE)));

        when(bookCopyService.getAllAvailableCopiesByTitleId(ArgumentMatchers.anyLong())).thenReturn(bookCopies);
        when(bookCopyMapper.mapToBookCopyDtoList(ArgumentMatchers.any())).thenReturn(bookCopiesDtos);

        mockMvc.perform(get("/library/copies/{titleId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addBookCopy() throws Exception {
        BookCopyDto bookCopyDto = new BookCopyDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopyDto);

        mockMvc.perform(post("/library/copies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void changeBookCopyStatus() throws Exception {

    }
}