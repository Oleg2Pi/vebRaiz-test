package com.polikarpov.vebraiztest.controller;

import com.polikarpov.vebraiztest.dto.SubscriptionDto;
import com.polikarpov.vebraiztest.dto.UserDto;
import com.polikarpov.vebraiztest.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.update(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Boolean> subscribeUser(@PathVariable("id") long id,
                                                 @RequestBody int subscriptionId) {
        return ResponseEntity.ok(userService.addSubscription(id, subscriptionId));
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDto>> getSubscriptions(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findAllSubscriptions(id));
    }

    @DeleteMapping("/{id}/subscriptions")
    public ResponseEntity<Boolean> deleteSubscription(@PathVariable("id") long id,
                                                      @RequestBody int subscriptionId) {
        return ResponseEntity.ok(userService.deleteSubscription(id, subscriptionId));
    }


}
