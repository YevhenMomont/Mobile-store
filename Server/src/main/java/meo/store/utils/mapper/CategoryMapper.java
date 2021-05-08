package meo.store.utils.mapper;

import org.mapstruct.Mapper;

import meo.store.dto.CategoryDto;
import meo.store.persistance.domain.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	Category dtoToCategory(CategoryDto categoryDto);

	CategoryDto categoryToDto(Category category);

}
