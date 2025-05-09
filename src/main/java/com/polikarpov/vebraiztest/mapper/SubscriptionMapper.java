package com.polikarpov.vebraiztest.mapper;

import com.polikarpov.vebraiztest.dto.SubscriptionDto;
import com.polikarpov.vebraiztest.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionDto toDto(Subscription subscription);
    Subscription fromDto(SubscriptionDto subscriptionDto);

}
