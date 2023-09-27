package com.example.userrestapi.service;

import com.example.userrestapi.entity.UserEntity;
import com.example.userrestapi.exception.InappropriateDateException;
import com.example.userrestapi.exception.UserNotFoundException;
import com.example.userrestapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity());
        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userService.getAllUsers();

        assertEquals(users.size(), result.size());
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Integer userId = 1;
        when(userRepository.getById(userId)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    }

    @Test
    void testGetUsersBetween() {
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);
        List<UserEntity> users = new ArrayList<>();
        when(userRepository.getUserEntitiesByDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqual(fromDate, toDate))
                .thenReturn(users);
        List<UserEntity> result = userService.getUsersBetween(fromDate, toDate);

        assertNotNull(result);
        verify(userRepository, times(1))
                .getUserEntitiesByDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqual(fromDate, toDate);
    }

    @Test
    void testGetUsersBetween_InappropriateDate() {
        LocalDate fromDate = LocalDate.of(2001, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);

        assertThrows(InappropriateDateException.class, () -> userService.getUsersBetween(fromDate, toDate));
    }
}