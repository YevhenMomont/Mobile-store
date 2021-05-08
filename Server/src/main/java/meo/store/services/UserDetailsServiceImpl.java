package meo.store.services;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import meo.store.dto.UserDto;
import meo.store.persistance.domain.Role;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDto user = service.findUserByEmail(email);

		return new User(user.getEmail(), user.getPassword(),
				Arrays.stream(Role.values())
						.map(role -> new SimpleGrantedAuthority(role.name()))
						.collect(Collectors.toList())
		);
	}
}
