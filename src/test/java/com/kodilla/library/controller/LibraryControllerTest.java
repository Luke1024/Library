package com.kodilla.library.controller;

import com.google.gson.Gson;
import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.Status;
import com.kodilla.library.domain.Title;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.*;
import com.kodilla.library.mapper.BookCopyMapper;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.BookCopyService;
import com.kodilla.library.service.RentService;
import com.kodilla.library.service.TitleService;
import com.kodilla.library.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {

    @MockBean
    private BookCopyMapper bookCopyMapper;
    @MockBean
    private TitleMapper titleMapper;
    @MockBean
    private UserMapper userMapper;

    @MockBean
    private BookCopyService bookCopyService;
    @MockBean
    private TitleService titleService;
    @MockBean
    private UserService userService;
    @MockBean
    private RentService rentService;

    @Autowired
    private MockMvc mockMvc;

    @Test //(expected = BookCopyNotFoundException.class)
    public void getUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(new User("Bruce", "Lee", LocalDate.now())));
        List<UserDto> userDtos = new ArrayList<>(Arrays.asList(new UserDto()));

        when(userService.getAllUsers()).thenReturn(users);
        when(userMapper.mapToUserDtoList(ArgumentMatchers.any())).thenReturn(userDtos);

        mockMvc.perform(get("/library/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getTitles() throws Exception {
        List<Title> titles = new ArrayList<>(Arrays.asList(new Title("Title", "Author", 2019)));
        List<TitleDto> titleDtos = new ArrayList<>(Arrays.asList(new TitleDto(1L, "Title", "Author", 2019)));

        when(titleService.getAllTitles()).thenReturn(titles);
        when(titleMapper.mapToTitlesDtoList(ArgumentMatchers.any())).thenReturn(titleDtos);

        mockMvc.perform(get("/library/titles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

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
    public void addUser() throws Exception {
        UserCreationDto userCreationDto = new UserCreationDto("Chuck", "Norris");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userCreationDto);

        mockMvc.perform(post("/library/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
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
        BookCopyStatusChangerDto bookCopyStatusChangerDto = new BookCopyStatusChangerDto(1L, Status.DESTROYED);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookCopyStatusChangerDto);

        mockMvc.perform(put("/library/copies/status")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void borrowABook() throws Exception {
        RentDto rentDto = new RentDto(1L, 1L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);

        mockMvc.perform(put("/library/rent/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void returnCopy() throws Exception {
        RentDto rentDto = new RentDto(1L, 1L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);

        mockMvc.perform(put("/library/rent/return")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}