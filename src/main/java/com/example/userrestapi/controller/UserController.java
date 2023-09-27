package com.example.userrestapi.controller;

import com.example.userrestapi.dto.UserDTO;
import com.example.userrestapi.entity.UserEntity;
import com.example.userrestapi.service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;


    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/between")
    public ResponseEntity<?> getUsersBetweenDate(@RequestParam("from")
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate from,
                                                 @RequestParam("to")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){
        return ResponseEntity.ok(userService.getUsersBetween(from, to));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserDTO newUser){
        return ResponseEntity.ok(userService.createUser(conversionService.convert(newUser, UserEntity.class)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid UserDTO newUser){
        return ResponseEntity.ok(userService.updateUser(id, conversionService.convert(newUser, UserEntity.class)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> saveUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User has been deleted successfully");
    }
}
