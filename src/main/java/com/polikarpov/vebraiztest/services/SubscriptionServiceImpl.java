package com.polikarpov.vebraiztest.services;

import com.polikarpov.vebraiztest.dto.SubscriptionDto;
import com.polikarpov.vebraiztest.entity.Subscription;
import com.polikarpov.vebraiztest.exceptions.NotFoundException;
import com.polikarpov.vebraiztest.mapper.SubscriptionMapper;
import com.polikarpov.vebraiztest.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements BaseService<SubscriptionDto, Integer> {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper mapper;

    @Override
    public SubscriptionDto create(SubscriptionDto entity) {
        return mapper.toDto(subscriptionRepository.save(mapper.fromDto(entity)));
    }

    @Transactional(readOnly = true)
    @Override
    public SubscriptionDto find(Integer id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription == null) {
            throw new NotFoundException("Subscription with id " + id + " not found");
        }
        return mapper.toDto(subscription);
    }

    @Override
    public boolean update(Integer id, SubscriptionDto entity) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription == null) {
            throw new NotFoundException("Subscription with id " + id + " not found");
        }
        subscription.setName(entity.name());
        subscriptionRepository.flush();
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Subscription subscription = subscriptionRepository.findById(id).orElse(null);
        if (subscription == null) {
            throw new NotFoundException("Subscription with id " + id + " not found");
        }
        subscriptionRepository.delete(subscription);
        return true;
    }

    public List<SubscriptionDto> getTopSubscriptions() {
        return subscriptionRepository.findTopThreeSubscriptions().stream()
                .map(mapper::toDto)
                .toList();
    }
}
