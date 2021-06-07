package meo.store.persistance.domain;

import java.time.LocalDateTime;
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
@Document(collection = "product")
public class Product {

	@Id
	private UUID uuid;

	private String title;

	private String description;

	private double price;

	private UUID categoryId;

	private UUID createdBy;

	private Double rating;

	@CreatedDate
	private LocalDateTime createdAt;

	private String imageUrl;

}
