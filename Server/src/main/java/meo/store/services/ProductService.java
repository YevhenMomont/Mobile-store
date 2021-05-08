package meo.store.services;

import java.util.List;
import java.util.UUID;

import meo.store.dto.ProductDto;

public interface ProductService {

	List<ProductDto> findAllProducts();

	ProductDto findUserById(UUID uuid);

	void deleteProduct(UUID uuid);

	ProductDto saveProduct(ProductDto productDto);

	ProductDto updateProduct(ProductDto productDto);

}
