package org.wellnessliving.homework03;

import org.junit.jupiter.api.Test;
import org.wellnessliving.homework03.dto.UserRegistrationDto;
import org.wellnessliving.homework03.dto.UserResponseDto;
import org.wellnessliving.homework03.exception.DataException;
import org.wellnessliving.homework03.repository.UserRepositoryImpl;
import org.wellnessliving.homework03.service.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    UserServiceImpl userService = new UserServiceImpl(userRepository);

    @Test
    void testRegister_Success() {
        Optional<UserResponseDto> dtoOptional = userService.registerUser(
                new UserRegistrationDto(
                        "test3@test.com", "12345678", "12345678", "+380501234567"
                )
        );
        assertTrue(dtoOptional.map(UserResponseDto::toString).isPresent());
    }

    @Test
    void testRegister_UniqueFail() {
        UserRegistrationDto registrationDtoFirst = new UserRegistrationDto(
                "test_unique@test.com", "12345678", "12345678", "+380501234567"
        );
        assertTrue(userService.registerUser(registrationDtoFirst).map(UserResponseDto::toString).isPresent());

        final UserRegistrationDto registrationDtoSecond = new UserRegistrationDto(
                "test_unique@test.com", "12345678", "12345678", "+380501234567"
        );
        assertThrowsExactly(DataException.class, () -> userService.registerUser(registrationDtoSecond));
    }

    @Test
    void testRegister_PasswordMismatch() {
        final UserRegistrationDto registrationDto = new UserRegistrationDto(
                "test_mismatch@test.com", "12345678", "02345679", "+380501234567"
        );
        assertThrowsExactly(RuntimeException.class, () -> userService.registerUser(registrationDto));
    }

    @Test
    void testRegister_EmailFormatFail() {
        UserRegistrationDto registrationDto = new UserRegistrationDto(
                "2test#formattest_com", "12345678", "12345678", "+380501234567"
        );
        assertThrowsExactly(RuntimeException.class, () -> userService.registerUser(registrationDto));
    }

    @Test
    void testFindById_Success() {
        Optional<UserResponseDto> dtoOptional = userService.getUserById(1L);
        assertTrue(dtoOptional.map(UserResponseDto::toString).isPresent());
    }

    @Test
    void testFindById_Fail() {
        Optional<UserResponseDto> dtoOptional = userService.getUserById(-1L);
        assertFalse(dtoOptional.map(UserResponseDto::toString).isPresent());
    }
}
