package demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

	@Override
	public void initialize(PasswordConstraint usernameConstraint) {

	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		return password != null && password.trim().length() > 0 && password.length() <= 16
				&& !Character.isDigit(password.charAt(0));
	}

}
