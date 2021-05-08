package meo.store.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import meo.store.dto.OrderDto;
import meo.store.persistance.repositories.OrderRepository;
import meo.store.utils.mapper.OrderMapper;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;

	private final OrderMapper mapper;

	@Override
	public List<OrderDto> findAllProducts() {
		return null;
	}

	@Override
	public OrderDto findUserById(UUID uuid) {
		return null;
	}

	@Override
	public void deleteProduct(UUID uuid) {

	}

	@Override
	public OrderDto saveProduct(OrderDto orderDto) {
		return null;
	}

	@Override
	public OrderDto updateProduct(OrderDto orderDto) {
		return null;
	}
}
