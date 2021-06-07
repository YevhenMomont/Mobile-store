package meo.store.services;

import java.util.List;
import java.util.UUID;

import meo.store.dto.CategoryDto;

public interface CategoryService {

	List<CategoryDto> findAllCategories();

	CategoryDto findCategoryById(UUID uuid);

	void deleteCategory(UUID uuid);

	CategoryDto saveCategory(CategoryDto CategoryDto);

	CategoryDto updateCategory(CategoryDto CategoryDto);
	
}
