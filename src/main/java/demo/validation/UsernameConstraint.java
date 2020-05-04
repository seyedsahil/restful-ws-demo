package demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = UsernameValidator.class)
public @interface UsernameConstraint {

	String message() default "username-invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
