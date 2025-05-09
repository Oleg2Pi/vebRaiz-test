package com.polikarpov.vebraiztest.controller;

import com.polikarpov.vebraiztest.dto.SubscriptionDto;
import com.polikarpov.vebraiztest.services.SubscriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionServiceImpl subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        return ResponseEntity.ok(subscriptionService.create(subscriptionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable("id") int id) {
        return ResponseEntity.ok(subscriptionService.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateSubscription(@PathVariable("id") int id, @RequestBody SubscriptionDto subscriptionDto) {
        return ResponseEntity.ok(subscriptionService.update(id, subscriptionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSubscription(@PathVariable("id") int id) {
        return ResponseEntity.ok(subscriptionService.delete(id));
    }

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionDto>> getTopSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }

}
