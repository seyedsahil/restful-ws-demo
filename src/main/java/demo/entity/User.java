package demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import demo.validation.FieldsEqualConstraint;
import demo.validation.PasswordConstraint;
import demo.validation.UsernameConstraint;

@FieldsEqualConstraint.List({ @FieldsEqualConstraint(field1 = "password", field2 = "retypePassword", message = "{error.global.passwordMisMatch}") })
@Entity
@Table(name = "user")
public class User {

	@Id
	@GenericGenerator(name = "user_id", strategy = "demo.generator.IdGenerator")
	@GeneratedValue(generator = "user_id")
	@Column(name = "user_id")
	private int id;

	@NotEmpty(message = "{error.field.name}")
	@Size(max = 32, message = "{error.field.name.size}")
	@Column(name = "name")
	private String name;

	@UsernameConstraint(message = "{error.field.username}")
	@Column(name = "username")
	private String username;

	@PasswordConstraint(message = "{error.field.password}")
	@Column(name = "password")
	private String password;

	@PasswordConstraint(message = "{error.field.retypePassword}")
	@Transient
	private String retypePassword;

	public User() {
		super();
	}

	public User(User user) {
		id = user.id;
		name = user.name;
		username = user.username;
		password = user.password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

}
