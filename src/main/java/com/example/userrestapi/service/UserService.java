package com.example.userrestapi.service;

import com.example.userrestapi.entity.UserEntity;
import com.example.userrestapi.exception.InappropriateDateException;
import com.example.userrestapi.exception.UserHasInappropriateAgeException;
import com.example.userrestapi.exception.UserNotFoundException;
import com.example.userrestapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (!RegistrationAgeUtil.isAllowedToRegister(userEntity.getDateOfBirth())) {
            throw new UserHasInappropriateAgeException("User has an inappropriate age to register");
        }
        return userRepository.save(userEntity);
    }

    public UserEntity updateUser(Integer id, UserEntity userEntity) {
        if (!RegistrationAgeUtil.isAllowedToRegister(userEntity.getDateOfBirth())) {
            throw new UserHasInappropriateAgeException("User has an inappropriate age to register");
        }
        return userRepository.save(userEntity);
    }

    public void deleteUser(Integer id) {
        if (Objects.isNull(userRepository.getById(id))) {
            throw new UserNotFoundException("User with given id doesn`t exist");
        }
        userRepository.deleteById(id);
    }

    public List<UserEntity> getUsersBetween(LocalDate from, LocalDate to) {
        if(from.isAfter(to)){
            throw new InappropriateDateException("From date is after from to");
        }
        return userRepository.getUserEntitiesByDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqual(from, to);
    }
}
