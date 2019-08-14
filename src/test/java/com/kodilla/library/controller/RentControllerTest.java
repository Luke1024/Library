package com.kodilla.library.controller;

import com.google.gson.Gson;
import com.kodilla.library.domain.*;
import com.kodilla.library.domain.dto.BorrowReturnDto;
import com.kodilla.library.domain.dto.RentDto;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.service.RentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTest {

    @MockBean
    private RentService rentService;

    @MockBean
    private RentMapper rentMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllRents() throws Exception {
        LocalDate localDate = LocalDate.now();
        User user = new User(1L, "Firstname", "Lastname", localDate);
        Title title = new Title(1L, "Title", "Author", 2019);
        BookCopy bookCopy = new BookCopy(1L,title, Status.AVAILABLE);
        List<Rent> rents = new ArrayList<>(Arrays.asList(new Rent(user, bookCopy, localDate)));
        List<RentDto> rentDtos = new ArrayList<>(Arrays.asList(new RentDto(1L, 1L, "Firstname",
                "Lastname", 1L, "Title", LocalDate.now(), localDate)));

        when(rentMapper.mapToDtoRentDtoList(ArgumentMatchers.anyList())).thenReturn(rentDtos);

        mockMvc.perform(get("/library/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].userFirstName", is("Firstname")))
                .andExpect(jsonPath("$[0].userLastName", is("Lastname")))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[0].bookTitle", is("Title")));
    }

    @Test
    public void getRent() throws Exception {
        LocalDate localDate = LocalDate.now();
        User user = new User(1L, "Firstname", "Lastname", localDate);
        Title title = new Title(1L, "Title", "Author", 2019);
        BookCopy bookCopy = new BookCopy(1L,title, Status.AVAILABLE);
        Rent rent = new Rent(user, bookCopy, localDate);
        RentDto rentDto = new RentDto(1L, 1L, "Firstname",
                "Lastname", 1L, "Title", LocalDate.now(), localDate);

        when(rentMapper.mapToRentDto(ArgumentMatchers.any())).thenReturn(rentDto);

        mockMvc.perform(get("/library/rent/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userFirstName", is("Firstname")))
                .andExpect(jsonPath("$.userLastName", is("Lastname")))
                .andExpect(jsonPath("$.bookId", is(1)))
                .andExpect(jsonPath("$.bookTitle", is("Title")));
    }

    @Test
    public void borrowABook() throws Exception {
        BorrowReturnDto borrowReturnDto = new BorrowReturnDto(1L, 1L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrowReturnDto);

        mockMvc.perform(put("/library/rent/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void returnCopy() throws Exception {
        BorrowReturnDto borrowReturnDto = new BorrowReturnDto(1L, 1L);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrowReturnDto);

        mockMvc.perform(put("/library/rent/return")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRent() throws Exception {
        mockMvc.perform(delete("/library/rent/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}