package meo.store.persistance.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {

	@Id
	private UUID id;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private Set<Role> roles = new HashSet<>();

	private Set<UUID> purchasedProducts = new HashSet<>();

	@CreatedDate
	private LocalDateTime createdAt;

}
