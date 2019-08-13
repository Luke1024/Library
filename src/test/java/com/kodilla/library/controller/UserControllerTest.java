package com.kodilla.library.controller;

import com.google.gson.Gson;
import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserCreationDto;
import com.kodilla.library.domain.dto.UserDto;
import com.kodilla.library.mapper.UserMapper;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
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
}