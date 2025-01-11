package amazonApi_v01.services.serviceImpl;

import amazonApi_v01.dto.brand.BrandResponseDto;
import amazonApi_v01.dto.category.CategoryResponseDto;
import amazonApi_v01.dto.product.ProductRequestDto;
import amazonApi_v01.dto.product.ProductResponseDto;
import amazonApi_v01.entity.Brand;
import amazonApi_v01.entity.Category;
import amazonApi_v01.entity.Product;
import amazonApi_v01.exception.DuplicateFieldException;
import amazonApi_v01.exception.ResourceNotFoundException;
import amazonApi_v01.repository.ProductRepository;
import amazonApi_v01.services.service.BrandService;
import amazonApi_v01.services.service.CategoryService;
import amazonApi_v01.services.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    @Override
    public List<ProductResponseDto> listAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductResponseDto> listProductsByPage(int pageNum, int pageSize,
                                                       List<String> sortFields,
                                                       List<Sort.Direction> directions) {

        List<Sort.Order> orders = new ArrayList<>();
        for (int i =0; i< sortFields.size(); i++) {
            orders.add(new Sort.Order(directions.get(i), sortFields.get(i)));
        }
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(orders));
            return productRepository
                    .findAll(pageable)
                    .stream().map(product -> modelMapper.map(product, ProductResponseDto.class))
                    .collect(Collectors.toList());

    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        if(productRepository.existsByName(productRequestDto.getName())){
            throw new DuplicateFieldException("Product Name : ", productRequestDto.getName());
        }
        Product product = new Product();
        mapDtoEntity(productRequestDto, product);
        Product saveProduct = productRepository.save(product);
        return modelMapper.map(saveProduct, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto updateProduct(Integer id, ProductRequestDto requestDto) {
       Product existingProd = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found With This id " + id));
        mapDtoEntity(requestDto, existingProd);
        Product saveProd = productRepository.save(existingProd);
        return modelMapper.map(saveProd, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto getProductById(Integer productId) {
       Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found With This Id "+ productId));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public void deleteProduct(Integer productId) {
      if(!productRepository.existsById(productId)){
          throw new ResourceNotFoundException("Product Not Found With This Id "+ productId);
      }
      productRepository.deleteById(productId);

    }

    @Override
    public List<ProductResponseDto> searchProductByName(String name) {
       return productRepository.findAll()
                .stream()
                .filter(product -> product.getName()
                        .toLowerCase().contains(name.toLowerCase()))
                .map(product -> modelMapper.map(product,ProductResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> searchProductByCategory(Integer categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        if(products.isEmpty()){
            throw new ResourceNotFoundException("No Product Found For Category Id " + categoryId);
        }
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }
    private void mapDtoEntity(ProductRequestDto requestDto, Product product){
        product.setName(requestDto.getName());
        product.setDiscountPercent(requestDto.getDiscountPercent());
        product.setCost(requestDto.getCost());
        product.setDescription(requestDto.getDescription());
        product.setInStock(requestDto.isInStock());
        product.setEnabled(requestDto.isEnabled());
        product.setMainImage(requestDto.getMainImage());
        product.setDiscountPercent(requestDto.getDiscountPercent());
        product.setPrice(requestDto.getPrice());

        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(requestDto.getCategoryId());
        Category category = modelMapper.map(categoryResponseDto, Category.class);
        product.setCategory(category);

        BrandResponseDto brandResponseDto = brandService.getBrandById(requestDto.getBrandId());
        Brand brand = modelMapper.map(brandResponseDto, Brand.class);
        product.setBrand(brand);

    }
//    public String saveImage(Product product, MultipartFile file){
//
//    }
}
