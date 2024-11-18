package org.wellnessliving.homework03;

import org.junit.jupiter.api.Test;
import org.wellnessliving.homework03.dto.UserRegistrationDto;
import org.wellnessliving.homework03.service.UserValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class UserValidatorTest {
    @Test
    void testPhoneSuccess() {
        UserRegistrationDto registrationDto = new UserRegistrationDto(
                "validate_01@test.com", "12345678", "12345678", "+380501234567"
        );
        assertDoesNotThrow(() -> UserValidator.validate(registrationDto));
    }

    @Test
    void testPhoneFail() {
        UserRegistrationDto registrationDto = new UserRegistrationDto(
                "validate_01@test.com", "12345678", "12345678", "050-123-45-67"
        );
        assertThrowsExactly(RuntimeException.class, () -> UserValidator.validate(registrationDto));
    }
}
