package demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldsEqualValidator implements ConstraintValidator<FieldsEqualConstraint, Object> {

	private String field1;

	private String field2;

	@Override
	public void initialize(FieldsEqualConstraint fieldsEqual) {
		field1 = fieldsEqual.field1();
		field2 = fieldsEqual.field2();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object field1Value = new BeanWrapperImpl(value).getPropertyValue(field1);
		Object field2Value = new BeanWrapperImpl(value).getPropertyValue(field2);
		if (field1Value != null) {
			return field1Value.equals(field2Value);
		} else {
			return field2Value == null;
		}
	}

}
