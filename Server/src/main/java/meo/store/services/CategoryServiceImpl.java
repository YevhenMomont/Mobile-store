package meo.store.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import meo.store.dto.CategoryDto;
import meo.store.persistance.domain.Category;
import meo.store.persistance.repositories.CategoryRepository;
import meo.store.utils.mapper.CategoryMapper;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;

	private final CategoryMapper mapper;

	@Override
	public List<CategoryDto> findAllCategories() {
		return repository.findAll()
				.stream()
				.map(mapper::categoryToDto)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto findCategoryById(UUID uuid) {
		return repository.findById(uuid)
				.map(mapper::categoryToDto)
				.orElseThrow();
	}

	@Override
	public void deleteCategory(UUID uuid) {
		repository.deleteById(uuid);
	}

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		Category category = new Category();

		category.setId(UUID.randomUUID());
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		category.setCreatedAt(LocalDateTime.now());

		return mapper.categoryToDto(repository.save(category));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		return mapper.categoryToDto(repository.save(mapper.dtoToCategory(categoryDto)));
	}

}
