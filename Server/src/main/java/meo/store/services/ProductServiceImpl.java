package meo.store.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import meo.store.dto.ProductDto;
import meo.store.persistance.domain.Product;
import meo.store.persistance.repositories.ProductRepository;
import meo.store.utils.mapper.ProductMapper;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	private final ProductMapper mapper;

	@Override
	public List<ProductDto> findAllProducts() {
		return repository.findAll()
				.stream()
				.map(mapper::productToDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto findUserById(UUID uuid) {
		return repository.findById(uuid)
				.map(mapper::productToDto)
				.orElseThrow();
	}

	@Override
	public void deleteProduct(UUID uuid) {
		repository.deleteById(uuid);
	}

	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		Product product = new Product();

		product.setUuid(UUID.randomUUID());
		product.setTitle(productDto.getTitle());
		product.setDescription(productDto.getDescription());
		product.setCategoryId(productDto.getCategoryId());

		return mapper.productToDto(repository.save(product));
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {
		return mapper.productToDto(repository.save(mapper.dtoToProduct(productDto)));
	}

}
