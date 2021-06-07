package meo.store.services;

import java.util.List;
import java.util.UUID;

import meo.store.dto.ProductDto;

public interface ProductService {

	List<ProductDto> findAllProducts();

	List<ProductDto> findAllProductsByCategoryId(UUID uuid);

	List<ProductDto> findAllByUuidIn(List<UUID> uuids);

	List<ProductDto> findAllProductsByCosineSimilarity(UUID uuid);

	List<ProductDto> findAllProductsByMSE(UUID uuid);

	List<ProductDto> findAllProductsByPearsonCorrelation(UUID uuid);

	ProductDto findProductById(UUID uuid);

	void deleteProduct(UUID uuid);

	ProductDto saveProduct(ProductDto productDto);

	ProductDto updateProduct(ProductDto productDto);

}
