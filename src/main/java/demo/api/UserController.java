package demo.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.User;
import demo.entity.model.UserEntityModelAssembler;
import demo.exception.ResourceNotFoundException;
import demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserEntityModelAssembler userAssembler;

	public UserController() {
		super();
	}

	@GetMapping("/api/users/{id}")
	public EntityModel<User> getUser(@PathVariable(name = "id") Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User doesn't exist."));
		return userAssembler.toModel(user);
	}

	@GetMapping("/api/users")
	public CollectionModel<EntityModel<User>> getUsers() {
		List<EntityModel<User>> users = userRepository.findAll().stream().map(userAssembler::toModel)
				.collect(Collectors.toList());
		return userAssembler.toModel(users);
	}

	@PostMapping("/api/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		User newUser = userRepository.save(user);
		EntityModel<User> userModel = userAssembler.toModel(newUser);
		return ResponseEntity.created(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userModel);
	}

	@PutMapping("/api/users")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User does not exist."));
		existingUser = userRepository.save(existingUser);
		EntityModel<User> userModel = userAssembler.toModel(existingUser);
		return ResponseEntity.created(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userModel);
	}

	@DeleteMapping("/api/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Integer userId) {
		userRepository.deleteById(userId);
		return ResponseEntity.noContent().build();
	}

}
