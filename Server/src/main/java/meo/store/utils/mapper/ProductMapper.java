package meo.store.utils.mapper;

import org.mapstruct.Mapper;

import meo.store.dto.ProductDto;
import meo.store.persistance.domain.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product dtoToProduct(ProductDto productDto);

	ProductDto productToDto(Product product);

}
