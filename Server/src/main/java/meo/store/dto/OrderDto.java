package meo.store.dto;

import java.util.UUID;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

	@Null
	UUID id;

	UUID userId;

	UUID productId;
}
