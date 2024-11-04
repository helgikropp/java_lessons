package org.wellnessliving.homework03.service;

import org.wellnessliving.homework03.dto.UserRegistrationDto;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Integer PWD_LENGTH_MIN = 8;
    private static final String ERR_MSG_EMAIL_EMPTY = "Email cannot be empty";
    private static final String ERR_MSG_EMAIL_FORMAT = "Email format is not valid";
    private static final String ERR_MSG_PHONE_FORMAT = "Phone number format is not valid";
    private static final String ERR_MSG_PASSWORD_LENGTH = "Password length must be at least 8 symbols";
    private static final String ERR_MSG_PASSWORD_MATCH = "Password and repeated password must be identical";

    public static void validate(UserRegistrationDto dto) throws RuntimeException {
        if (dto.email().isEmpty()) {
            throw new RuntimeException(ERR_MSG_EMAIL_EMPTY);
        }

        if (!dto.email().contains("@")) {
            throw new RuntimeException(ERR_MSG_EMAIL_FORMAT);
        }

        Pattern regexPhone = Pattern.compile(
            "^[+]{1}[0-9]{12}$",
            Pattern.CASE_INSENSITIVE
        );
        if (!dto.phoneNumber().isEmpty() && !regexPhone.matcher(dto.phoneNumber()).matches()) {
            throw new RuntimeException(ERR_MSG_PHONE_FORMAT);
        }

        if (dto.password().length() < PWD_LENGTH_MIN) {
            throw new RuntimeException(ERR_MSG_PASSWORD_LENGTH);
        }

        if (!dto.password().equals(dto.repeatPassword())) {
            throw new RuntimeException(ERR_MSG_PASSWORD_MATCH);
        }
    }
}
