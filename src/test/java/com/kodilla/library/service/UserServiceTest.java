package com.kodilla.library.service;

import com.kodilla.library.controller.controllerExceptions.LibraryDatabaseException;
import com.kodilla.library.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    public void getAllUsers() {
        User user1 = new User("Firstname1", "Lastname1", LocalDate.now());
        userService.addUser(user1);
        User user2 = new User("Firstname2", "Lastname2", LocalDate.now());
        userService.addUser(user2);

        List<User> users = userService.getAllUsers();

        List<User> usersFiltered = users.stream()
                .filter(u -> u.getId()==user1.getId() || u.getId()==user2.getId()).collect(Collectors.toList());

        assertThat(user1, sameBeanAs(usersFiltered.get(0)));
        assertThat(user2, sameBeanAs(usersFiltered.get(1)));

        //clean up
        userService.deleteUserById(user1.getId());
        userService.deleteUserById(user2.getId());
    }

    @Test
    public void findUserById_addUser() throws LibraryDatabaseException {
        User user1 = new User("Firstname1", "Lastname1", LocalDate.now());
        userService.addUser(user1);

        assertThat(user1, sameBeanAs(userService.getUserById(user1.getId())));

        //clean up
        userService.deleteUserById(user1.getId());
    }

    @Test(expected = LibraryDatabaseException.class)
    public void deleteUserById() throws LibraryDatabaseException {
        User user1 = new User("Firstname1", "Lastname1", LocalDate.now());
        userService.addUser(user1);

        Long id = user1.getId();

        assertThat(user1, sameBeanAs(userService.getUserById(id)));

        userService.deleteUserById(id);

        userService.deleteUserById(id);
    }
}