package meo.store.controllers;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import meo.store.controllers.url.ProductUrl;
import meo.store.dto.ProductDto;
import meo.store.services.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;

	@GetMapping(value = ProductUrl.RESOURCE_NAME)
	public List<ProductDto> getProducts() {
		return service.findAllProducts();
	}

	@GetMapping(value = ProductUrl.GET_BY_CATEGORY)
	public List<ProductDto> getProductsByCategoryId(@PathVariable UUID uuid) {
		return service.findAllProductsByCategoryId(uuid);
	}

	@GetMapping(value = ProductUrl.GET_BY_COSINE_SIMILARITY)
	public List<ProductDto> getProductsByCosineSimilarity(@PathVariable UUID uuid) {
		return service.findAllProductsByCosineSimilarity(uuid);
	}

	@GetMapping(value = ProductUrl.GET_BY_MSE)
	public List<ProductDto> getProductsByMSE(@PathVariable UUID uuid) {
		return service.findAllProductsByMSE(uuid);
	}

	@GetMapping(value = ProductUrl.GET_BY_PEARSON_CORRELATION)
	public List<ProductDto> getProductsByPearsonCorrelation(@PathVariable UUID uuid) {
		return service.findAllProductsByPearsonCorrelation(uuid);
	}

	@GetMapping(value = ProductUrl.GET)
	public ProductDto getProduct(@PathVariable UUID uuid) {
		return service.findProductById(uuid);
	}

	@PostMapping(value = ProductUrl.CREATE)
	public ProductDto saveProduct(@Valid @RequestBody ProductDto productDto) {
		return service.saveProduct(productDto);
	}

	@PutMapping(value = ProductUrl.UPDATE)
	public ProductDto updateProduct(@RequestBody ProductDto productDto) { return service.updateProduct(productDto); }

}
