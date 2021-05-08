package meo.store.services;

import java.util.List;
import java.util.UUID;

import meo.store.dto.UserDto;

public interface UserService {

	List<UserDto> findAllUsers();

	UserDto findUserById(UUID uuid);

	UserDto findUserByEmail(String Email);

	void deleteUser(UUID uuid);

	UserDto saveUser(UserDto user);

	UserDto updateUser(UserDto user);
}
