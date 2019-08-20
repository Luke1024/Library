package com.kodilla.library.mapper;

import com.kodilla.library.domain.User;
import com.kodilla.library.domain.dto.UserCreationDto;
import com.kodilla.library.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public List<UserDto> mapToUserDtoList(final List<User> userList){
        return userList
                .stream()
                .map(u -> new UserDto(u.getId()
                        , u.getFirstName()
                        , u.getLastName()
                        , u.getRegistrationDate()))
                .collect(Collectors.toList());
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(user.getId()
                , user.getFirstName()
                , user.getLastName()
                , user.getRegistrationDate());
    }

    public User mapToUserFromCreationDto(final UserCreationDto userCreationDto){
        return new User(
                userCreationDto.getFirstName(),
                userCreationDto.getLastName(),
                LocalDate.now()
        );
    }
}
