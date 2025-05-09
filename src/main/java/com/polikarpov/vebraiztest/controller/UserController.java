package com.polikarpov.vebraiztest.controller;

import com.polikarpov.vebraiztest.dto.UserDto;
import com.polikarpov.vebraiztest.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
