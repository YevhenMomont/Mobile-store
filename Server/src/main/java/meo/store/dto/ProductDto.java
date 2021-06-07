package meo.store.dto;

import java.time.LocalDateTime;
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

	private double price;

	private UUID categoryId;

	private UUID createdBy;

	private Double rating;

	@Null
	private LocalDateTime createdAt;

	private String imageUrl;

}
