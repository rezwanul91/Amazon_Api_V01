package amazonApi_v01.services.service;

import amazonApi_v01.dto.category.CategoryRequestDto;
import amazonApi_v01.dto.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto);
    CategoryResponseDto getCategoryById(Long id);
    List<CategoryResponseDto> getCategoryByBrandId(Long brandId);
    void deleteCategory(Long id);

}
