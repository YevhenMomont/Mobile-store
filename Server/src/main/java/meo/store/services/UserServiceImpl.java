package meo.store.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import meo.store.dto.UserDto;
import meo.store.persistance.domain.Role;
import meo.store.persistance.domain.User;
import meo.store.persistance.repositories.UserRepository;
import meo.store.utils.mapper.UserMapper;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final PasswordEncoder encoder;

	private final UserRepository repository;

	private final UserMapper mapper;


	@Override
	public List<UserDto> findAllUsers() {
		return repository.findAll()
				.stream()
				.map(mapper::dtoToUser)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto findUserById(UUID uuid) {
		return repository.findById(uuid)
				.map(mapper::dtoToUser)
				.orElseThrow();
	}

	@Override
	public UserDto findUserByEmail(String email) {
		return repository.findByEmail(email)
				.map(mapper::dtoToUser)
				.orElseThrow();
	}

	@Override
	public void deleteUser(UUID uuid) {
		repository.deleteById(uuid);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = new User();

		user.setId(UUID.randomUUID());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.getRoles().add(Role.USER);

		return mapper.dtoToUser(repository.save(user));
	}

	@Override
	public UserDto updateUser(UserDto user) {
		return mapper.dtoToUser(repository.save(mapper.userToDto(user)));
	}

}
