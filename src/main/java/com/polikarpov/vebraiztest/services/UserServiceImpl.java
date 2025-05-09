package com.polikarpov.vebraiztest.services;

import com.polikarpov.vebraiztest.dto.SubscriptionDto;
import com.polikarpov.vebraiztest.dto.UserDto;
import com.polikarpov.vebraiztest.entity.Subscription;
import com.polikarpov.vebraiztest.entity.User;
import com.polikarpov.vebraiztest.exceptions.NotFoundException;
import com.polikarpov.vebraiztest.mapper.SubscriptionMapper;
import com.polikarpov.vebraiztest.mapper.UserMapper;
import com.polikarpov.vebraiztest.repositories.SubscriptionRepository;
import com.polikarpov.vebraiztest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements BaseService<UserDto, Long> {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserMapper mapper;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public UserDto create(UserDto entity) {
        log.info("Creating user: {}", entity);
        return mapper.toDto(userRepository.save(mapper.fromDto(entity)));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto find(Long id) {
        UserDto userDto = mapper.toDto(userRepository.findById(id).orElse(null));
        if (userDto == null) {
            throw new NotFoundException("User " + id + " not found");
        }
        log.info("Found user: {}", userDto);
        return userDto;
    }

    @Override
    public boolean update(Long id, UserDto entity) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User " + id + " not found");
        }
        user.setEmail(entity.email());
        user.setFirstName(entity.firstName());
        user.setLastName(entity.lastName());
        userRepository.flush();
        log.info("Updating user: {}", user);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User " + id + " not found");
        }
        log.info("Deleting user: {}", user);
        userRepository.delete(user);
        return true;
    }

    public boolean addSubscription(Long userId, Integer subscriptionId) {
        User user = userRepository.findById(userId).orElse(null);
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
        if (user == null || subscription == null) {
            throw new NotFoundException("User " + userId + " or subscription " + subscriptionId + " not found");
        }

        if (!user.getSubscriptions().contains(subscription)) {
            user.getSubscriptions().add(subscription);
            userRepository.flush();
            log.info("add subscription {} to user {}", subscriptionId, user);
            return true;
        }
        log.info("subscription {} already added to user {}", subscriptionId, user);
        return false;
    }

    public List<SubscriptionDto> findAllSubscriptions(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException("User " + userId + " not found");
        }
        log.info("Finding all subscriptions for user {}", user);
        return user.getSubscriptions().stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }

    public boolean deleteSubscription(Long userId, Integer subscriptionId) {
        User user = userRepository.findById(userId).orElse(null);
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
        if (user == null || subscription == null) {
            throw new NotFoundException("User " + userId + " or subscription " + subscriptionId + " not found");
        }

        if (user.getSubscriptions().contains(subscription)) {
            user.getSubscriptions().remove(subscription);
            userRepository.flush();
            log.info("User delete {}", user);
            return true;
        }
        log.info("Don't delete user {}", user);
        return false;
    }
}
