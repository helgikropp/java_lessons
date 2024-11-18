package org.wellnessliving.homework03.dto;

public record UserRegistrationDto(
        String email,
        String password,
        String repeatPassword,
        String phoneNumber
) {
}
