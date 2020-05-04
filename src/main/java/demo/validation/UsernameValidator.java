package demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

	@Override
	public void initialize(UsernameConstraint usernameConstraint) {

	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return username != null && username.trim().length() > 0 && username.length() <= 16
				&& !Character.isDigit(username.charAt(0));
	}

}
