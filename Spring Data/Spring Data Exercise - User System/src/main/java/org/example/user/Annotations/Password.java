package org.example.user.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Invalid password";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minLength();
    int maxLength();

    boolean containsLowercase() default false;
    boolean containsUppercase() default false;
    boolean containsDigit() default false;
    boolean containsSpecialSymbol() default false;

}
