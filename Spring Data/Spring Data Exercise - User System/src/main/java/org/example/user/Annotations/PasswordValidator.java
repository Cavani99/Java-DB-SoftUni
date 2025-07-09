package org.example.user.Annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int minLength;
    private int maxLength;

    private boolean containsLowercase;
    private boolean containsUppercase;
    private boolean containsDigit;
    private boolean containsSpecialSymbol;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.containsLowercase = constraintAnnotation.containsLowercase();
        this.containsUppercase = constraintAnnotation.containsUppercase();
        this.containsDigit = constraintAnnotation.containsDigit();
        this.containsSpecialSymbol = constraintAnnotation.containsSpecialSymbol();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return false;
        if (value.length() < minLength || value.length() > maxLength) return false;

        if (containsLowercase && !value.matches(".*[a-z].*")) return false;
        if (containsUppercase && !value.matches(".*[A-Z].*")) return false;
        if (containsDigit && !value.matches(".*\\d.*")) return false;

        return !containsSpecialSymbol || value.matches(".*[!@#$%^&*()_+\\-={}:;\"'<>?,./`~\\[\\]\\\\|].*");
    }
}
