package amazonApi_v01.services.service;

import amazonApi_v01.dto.product.ProductRequestDto;
import amazonApi_v01.dto.product.ProductResponseDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> listAllProducts();
    List<ProductResponseDto> listProductsByPage(int pageNum, int pageSize,
                                                List<String> sortFields, List<Sort.Direction> directions);
    ProductResponseDto saveProduct(ProductRequestDto productRequestDto);
    ProductResponseDto updateProduct(Integer id, ProductRequestDto requestDto);
    ProductResponseDto getProductById(Integer productId);
    void deleteProduct(Integer productId);
    List<ProductResponseDto> searchProductByName(String name);
    List<ProductResponseDto> searchProductByCategory(Integer categoryId);
}
