package amazonApi_v01.services.serviceImpl;

import amazonApi_v01.dto.brand.BrandResponseDto;
import amazonApi_v01.dto.category.CategoryRequestDto;
import amazonApi_v01.dto.category.CategoryResponseDto;
import amazonApi_v01.entity.Brand;
import amazonApi_v01.entity.Category;
import amazonApi_v01.exception.ResourceAlreadyExistsException;
import amazonApi_v01.exception.ResourceNotFoundException;
import amazonApi_v01.repository.BrandRepository;
import amazonApi_v01.repository.CategoryRepository;
import amazonApi_v01.services.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BrandRepository brandRepository;
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        if(categoryRepository.existsByName(categoryRequestDto.getName())){
            throw new ResourceAlreadyExistsException("Category With Name " + categoryRequestDto.getName() +
                    " Already Exist");
        }
        Category category = modelMapper.map(categoryRequestDto, Category.class);
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
       Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category With Id " + id + " Not Found"));
       modelMapper.map(categoryRequestDto, category);
       Category newCategory = categoryRepository.save(category);
       return modelMapper.map(newCategory, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category With Id " + id + " Not Found"));
        return modelMapper.map(category,CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getCategoryByBrandId(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(()-> new ResourceNotFoundException("Brand With Id " + brandId + " Not Found"));
        //get categories from the brand
        Set<Category> categories = brand.getCategories();

        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Category With Id " + id + "Not Found"));
        if(!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Category Already Deleted With Id " + id );
        }
        categoryRepository.deleteById(id);

    }
}
