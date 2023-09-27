package com.example.userrestapi.service;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class RegistrationAgeUtil {

    @Value("${ageOfRegistration}")
    private static Integer ageOfRegistration;

    public static boolean isAllowedToRegister(LocalDate date){
        return LocalDate.now().getYear() - date.getYear() < ageOfRegistration;
    }
}
