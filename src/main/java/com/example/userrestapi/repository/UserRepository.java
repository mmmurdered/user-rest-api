package com.example.userrestapi.repository;

import com.example.userrestapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> getUserEntitiesByDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqual(LocalDate from, LocalDate to);
}
