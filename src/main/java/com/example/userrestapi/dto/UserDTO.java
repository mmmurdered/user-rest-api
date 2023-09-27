package com.example.userrestapi.dto;

import com.example.userrestapi.util.ValidationMessage;
import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserDTO {

    @NotNull(message = ValidationMessage.EMAIL_EMPTY)
    @Email(message = ValidationMessage.EMAIL_VALIDATION)
    @Column(unique = true)
    private String email;

    @NotNull(message = ValidationMessage.FIRST_NAME_EMPTY)
    private String firstName;

    @NotNull(message = ValidationMessage.LAST_NAME_EMPTY)
    private String lastName;

    @NotNull(message = ValidationMessage.DATE_EMPTY)
    private LocalDate dateOfBirth;
}
