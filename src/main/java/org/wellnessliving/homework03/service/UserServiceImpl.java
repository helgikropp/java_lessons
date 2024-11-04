package org.wellnessliving.homework03.service;

import org.wellnessliving.homework03.dto.UserRegistrationDto;
import org.wellnessliving.homework03.dto.UserResponseDto;
import org.wellnessliving.homework03.repository.UserMapper;
import org.wellnessliving.homework03.repository.UserRepositoryImpl;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserResponseDto> registerUser(UserRegistrationDto userDto) {
        return userRepository.save(UserMapper.toUser(userDto)).map(UserMapper::toResponseDto);
    }

    @Override
    public Optional<UserResponseDto> getUserById(Long userId) {
        return userRepository.findById(userId).map(UserMapper::toResponseDto);
    }
}
