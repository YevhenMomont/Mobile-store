package meo.store.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meo.store.persistance.domain.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@Null
	private UUID id;

	@NotNull
	@Email
	private String email;

	private String firstName;

	private String lastName;

	@NotBlank
	private String password;

	private Set<Role> roles = new HashSet<>();

}
