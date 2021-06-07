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
public class RecordDto {

	@Null
	UUID id;

	UUID userId;

	UUID productId;

	float ratingByUser;

	@Null
	private LocalDateTime createdAt;

}
