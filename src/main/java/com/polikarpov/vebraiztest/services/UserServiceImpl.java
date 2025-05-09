package com.polikarpov.vebraiztest.services;

import com.polikarpov.vebraiztest.dto.UserDto;
import com.polikarpov.vebraiztest.entity.User;
import com.polikarpov.vebraiztest.exceptions.NotFoundException;
import com.polikarpov.vebraiztest.mapper.UserMapper;
import com.polikarpov.vebraiztest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements BaseService<UserDto, Long> {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserDto create(UserDto entity) {
        return mapper.userToUserDto(userRepository.save(mapper.userDtoToUser(entity)));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto find(Long id) {
        UserDto userDto = mapper.userToUserDto(userRepository.findById(id).orElse(null));
        if (userDto == null) {
            throw new NotFoundException("User " + id + " not found");
        }
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
        return true;
    }

    @Override
    public boolean delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User " + id + " not found");
        }
        userRepository.delete(user);
        return true;
    }
}
