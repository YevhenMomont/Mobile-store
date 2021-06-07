package meo.store.services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import meo.store.dto.ProductDto;
import meo.store.persistance.domain.Product;
import meo.store.persistance.repositories.ProductRepository;
import meo.store.services.recommender.RecommendService;
import meo.store.utils.mapper.ProductMapper;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	private RecommendService recommendService;

	private final ProductMapper mapper;

	@Override
	public List<ProductDto> findAllProducts() {
		return repository.findAll()
				.stream()
				.map(mapper::productToDto)
				.collect(Collectors.toList()).stream()
				.sorted(Comparator.comparing(ProductDto::getRating))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> findAllProductsByCategoryId(UUID uuid) {
		return repository.findAllByCategoryId(uuid)
				.stream()
				.map(product -> mapper.productToDto(product.orElseThrow()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> findAllByUuidIn(List<UUID> uuids) {
		return repository.findAllByUuidIn(uuids)
				.stream()
				.map(mapper::productToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> findAllProductsByCosineSimilarity(UUID uuid) {
		return recommendService.getRecommendationByCosineSimilarity(uuid);
	}

	@Override
	public List<ProductDto> findAllProductsByMSE(UUID uuid) {
		return recommendService.getRecommendationByMSE(uuid);
	}

	@Override
	public List<ProductDto> findAllProductsByPearsonCorrelation(UUID uuid) {
		return recommendService.getRecommendationByPearsonCorrelation(uuid);
	}

	@Override
	public ProductDto findProductById(UUID uuid) {
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
		product.setCreatedBy(productDto.getCreatedBy());
		product.setRating(0d);
		product.setCreatedAt(LocalDateTime.now());

		return mapper.productToDto(repository.save(product));
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {
		return mapper.productToDto(repository.save(mapper.dtoToProduct(productDto)));
	}

	@Autowired
	public void setRecommendService(RecommendService recommendService) {
		this.recommendService = recommendService;
	}
}
