package meo.store.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	@Null
	private UUID id;

	private String title;

	private String description;

	@Null
	private LocalDateTime createdAt;

	private String imageUrl;

}
