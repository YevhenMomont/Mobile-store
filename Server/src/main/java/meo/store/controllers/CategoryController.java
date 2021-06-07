package meo.store.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import meo.store.controllers.url.CategoryUrl;
import meo.store.dto.CategoryDto;
import meo.store.services.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService service;

	@GetMapping(value = CategoryUrl.RESOURCE_NAME)
	public List<CategoryDto> getCategories() {return service.findAllCategories();}

	@GetMapping(value = CategoryUrl.GET_BY_ID)
	public CategoryDto getCategory(@PathVariable UUID uuid) {return service.findCategoryById(uuid);}

	@PostMapping(value = CategoryUrl.CREATE)
	public CategoryDto saveCategory(@Valid @RequestBody CategoryDto categoryDto) {return service.saveCategory(categoryDto);}

}
