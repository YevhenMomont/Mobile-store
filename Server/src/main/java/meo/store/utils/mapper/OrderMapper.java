package meo.store.utils.mapper;

import org.mapstruct.Mapper;

import meo.store.dto.OrderDto;
import meo.store.persistance.domain.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	Order dtoToOrder(OrderDto orderDto);

	OrderDto orderToDto(Order order);

}
