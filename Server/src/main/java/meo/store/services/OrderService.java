package meo.store.services;

import java.util.List;
import java.util.UUID;

import meo.store.dto.OrderDto;

public interface OrderService {

	List<OrderDto> findAllProducts();

	OrderDto findUserById(UUID uuid);

	void deleteProduct(UUID uuid);

	OrderDto saveProduct(OrderDto orderDto);

	OrderDto updateProduct(OrderDto orderDto);

}
