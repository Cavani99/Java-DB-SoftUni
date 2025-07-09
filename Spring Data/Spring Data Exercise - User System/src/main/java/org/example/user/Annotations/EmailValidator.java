package org.example.user.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Password, String> {

    private static final String USER_REGEX =
            "^[a-zA-Z0-9]+([._-][a-zA-Z0-9]+)*$";

    private static final String HOST_REGEX =
            "^[a-zA-Z]+(-[a-zA-Z]+)*" +
                    "(\\.[a-zA-Z]+(-[a-zA-Z]+)*)+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return false;

        String[] parts = value.split("@");
        if (parts.length != 2) return false;

        String user = parts[0];
        String host = parts[1];

        return user.matches(USER_REGEX) && host.matches(HOST_REGEX);
    }
}
