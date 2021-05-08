package meo.store.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import meo.store.controllers.url.UserUrl;
import meo.store.dto.UserDto;
import meo.store.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping(value = UserUrl.RESOURCE_NAME)
	public List<UserDto> getUsers() {return userService.findAllUsers();}

	@GetMapping(value = UserUrl.GET_BY_ID)
	public UserDto findUserById(@PathVariable UUID uuid) {
		return userService.findUserById(uuid);
	}

	@GetMapping(value = UserUrl.GET_BY_EMAIL)
	public UserDto getUser(Authentication authentication) {return userService.findUserByEmail(((User) authentication.getPrincipal()).getUsername());}

	@PostMapping(value = UserUrl.CREATE)
	public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
		return userService.saveUser(userDto);
	}

}
