package org.wellnessliving.homework03.service;

import org.wellnessliving.homework03.dto.UserRegistrationDto;
import org.wellnessliving.homework03.dto.UserResponseDto;

import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> registerUser(UserRegistrationDto userDto);

    Optional<UserResponseDto> getUserById(Long userId);
}
