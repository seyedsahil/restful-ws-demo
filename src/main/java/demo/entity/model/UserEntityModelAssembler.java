package demo.entity.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import demo.api.UserController;
import demo.entity.User;

@Component
public class UserEntityModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	@Override
	public EntityModel<User> toModel(User user) {
		return new EntityModel<User>(user, linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).getUsers()).withRel("all-users"));
	}

	public CollectionModel<EntityModel<User>> toModel(List<EntityModel<User>> users) {
		return new CollectionModel<EntityModel<User>>(users);
	}

}