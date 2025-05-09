package com.polikarpov.vebraiztest.mapper;

import com.polikarpov.vebraiztest.dto.UserDto;
import com.polikarpov.vebraiztest.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User fromDto(UserDto userDto);

}
