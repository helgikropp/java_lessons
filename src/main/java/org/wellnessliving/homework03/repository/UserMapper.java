package org.wellnessliving.homework03.repository;

import org.wellnessliving.homework03.dto.UserRegistrationDto;
import org.wellnessliving.homework03.dto.UserResponseDto;
import org.wellnessliving.homework03.entity.User;
import org.wellnessliving.homework03.service.UserValidator;

public class UserMapper {
    public static UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getEmail(), user.getPhoneNumber());
    }

    public static User toUser(UserRegistrationDto dto) {
        UserValidator.validate(dto);

        User user = new User();
        user.setEmail(dto.email());
        user.setPhoneNumber(dto.phoneNumber());
        user.setPassword(dto.password());
        return user;
    }
}
