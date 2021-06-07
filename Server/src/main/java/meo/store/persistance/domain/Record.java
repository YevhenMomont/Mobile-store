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
@Document(collection = "record")
public class Record {

	@Id
	UUID id;

	UUID userId;

	UUID productId;

	float ratingByUser;

	@CreatedDate
	private LocalDateTime createdAt;

}
