package com.kodilla.library.controller;

import com.google.gson.Gson;
import com.kodilla.library.domain.dto.BorrowReturnDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}