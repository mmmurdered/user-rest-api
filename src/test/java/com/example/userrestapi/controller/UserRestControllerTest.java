package com.example.userrestapi.controller;

import com.example.userrestapi.entity.UserEntity;
import com.example.userrestapi.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private ConversionService conversionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetUsers() {
        UserEntity firstUser = new UserEntity(1, "sdfgsdfg", "dgsfdgd",
                "dfgdfgdfg", LocalDate.now());
        UserEntity secondUser = new UserEntity(2, "sdfg234", "dgsfdgd",
                "dfgdfgdfg", LocalDate.now());

        List<UserEntity> allUsers = Arrays.asList(firstUser, secondUser);
        when(userService.getAllUsers()).thenReturn(allUsers);
        ResponseEntity<?> responseEntity = userController.getUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUsersBetweenDate() {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 12, 31);
        UserEntity firstUser = new UserEntity(1, "sdfgsdfg", "dgsfdgd",
                "dfgdfgdfg", from);
        UserEntity secondUser = new UserEntity(2, "sdfg234", "dgsfdgd",
                "dfgdfgdfg", to);

        List<UserEntity> allUsers = Arrays.asList(firstUser, secondUser);
        when(userService.getUsersBetween(from, to)).thenReturn(allUsers);
        ResponseEntity<?> responseEntity = userController.getUsersBetweenDate(from, to);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userService, times(1)).getUsersBetween(from, to);
    }


    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        ResponseEntity<?> responseEntity = userController.saveUser(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }
}
