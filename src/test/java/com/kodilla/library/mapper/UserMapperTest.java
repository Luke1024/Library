package com.kodilla.library.mapper;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserCreationDto;
import com.kodilla.library.domain.dto.UserDto;
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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mapToUserDtoList() {
        List<User> userList = new ArrayList<>(Arrays.asList(new User(1L, "Firstname", "Lastname", LocalDate.now())));
        List<UserDto> userDtoList = new ArrayList<>(Arrays.asList(new UserDto(1L, "Firstname", "Lastname", LocalDate.now())));

        assertThat(userDtoList, sameBeanAs(userList));
    }

    @Test
    public void mapToUserFromCreationDto() {
        UserCreationDto userCreationDto = new UserCreationDto("Firstname", "Lastname");
        User user = new User("Firstname", "Lastname", LocalDate.now());

        assertThat(user, sameBeanAs(userMapper.mapToUserFromCreationDto(userCreationDto)));
    }
}