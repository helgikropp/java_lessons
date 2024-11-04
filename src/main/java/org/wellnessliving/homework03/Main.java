package org.wellnessliving.homework03;

import org.wellnessliving.homework03.dto.UserRegistrationDto;
import org.wellnessliving.homework03.dto.UserResponseDto;
import org.wellnessliving.homework03.repository.UserRepositoryImpl;
import org.wellnessliving.homework03.service.UserServiceImpl;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        Optional<UserResponseDto> dtoOptional;

        dtoOptional = userService.registerUser(
            new UserRegistrationDto(
                "test1@test.com", "12345678", "12345678", "+380501234567"
            )
        );
        System.out.println("Registered: " + dtoOptional.map(UserResponseDto::toString));

        dtoOptional = userService.registerUser(
                new UserRegistrationDto(
                        "test2@test.com", "12345678", "12345678", "+380501234567"
                )
        );
        System.out.println("Registered: " + dtoOptional.map(UserResponseDto::toString));

        dtoOptional = userService.getUserById(2L);
        System.out.println("Retrieved: " + dtoOptional.map(UserResponseDto::toString));
    }
}
