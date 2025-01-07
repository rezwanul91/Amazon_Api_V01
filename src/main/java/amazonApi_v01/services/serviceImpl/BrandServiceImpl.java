package amazonApi_v01.services.serviceImpl;

import amazonApi_v01.dto.brand.BrandRequestDto;
import amazonApi_v01.dto.brand.BrandResponseDto;
import amazonApi_v01.entity.Brand;
import amazonApi_v01.entity.Category;
import amazonApi_v01.exception.ResourceAlreadyExistsException;
import amazonApi_v01.exception.ResourceNotFoundException;
import amazonApi_v01.repository.BrandRepository;
import amazonApi_v01.repository.CategoryRepository;
import amazonApi_v01.services.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public BrandResponseDto createBrand(BrandRequestDto brandRequestDto) {
        if(brandRepository.existsByName(brandRequestDto.getName())){
            throw new ResourceAlreadyExistsException("Brand with name " +
                    brandRequestDto.getName() + " already exists");
        }
    Brand brand = modelMapper.map(brandRequestDto, Brand.class);
      Set<Category> categorySet = categoryRepository.findAllById(brandRequestDto
              .getCategoryIds()).stream().collect(Collectors.toSet());
      brand.setCategories(categorySet);
    Brand savedBrand =  brandRepository.save(brand);

        return modelMapper.map(savedBrand, BrandResponseDto.class);
    }

    @Override
    public List<BrandResponseDto> getAllBrand() {
        return brandRepository.findAll().stream()
                .map(getBrand -> modelMapper.map(getBrand, BrandResponseDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public BrandResponseDto getBrandById(Long id) {
        Brand brand = brandRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Brand With id " + id + " Not Found"));
        return modelMapper.map(brand, BrandResponseDto.class);
    }

    @Override
    public BrandResponseDto updateBrandById(Long id, BrandRequestDto requestDto) {
        Brand existingBrand = brandRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand With id " + id + " Not Found"));
        existingBrand.setName(requestDto.getName());
        existingBrand.setLogo(requestDto.getLogo());
        Set<Category> categorySet = categoryRepository.findAllById(requestDto
                .getCategoryIds()).stream().collect(Collectors.toSet());
        existingBrand.setCategories(categorySet);
        modelMapper.map(requestDto, Brand.class);
        Brand newBrands = brandRepository.save(existingBrand);
        return modelMapper.map(newBrands, BrandResponseDto.class);
    }

    @Override
    public void deleteBrandById(Long id) {
        if(!brandRepository.existsById(id)){
            throw new ResourceNotFoundException("Brand Already Deleted With Id " + id );
        }
        brandRepository.deleteById(id);
    }
}
