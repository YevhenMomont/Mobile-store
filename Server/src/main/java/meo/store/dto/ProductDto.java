package meo.store.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	@Null
	private UUID uuid;

	@NotBlank
	private String title;

	private String description;

	private UUID categoryId;

	private UUID createdBy;
}
