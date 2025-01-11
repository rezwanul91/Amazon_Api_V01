package amazonApi_v01.controller;

import amazonApi_v01.dto.product.ProductRequestDto;
import amazonApi_v01.dto.product.ProductResponseDto;
import amazonApi_v01.services.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> createProducts(@Valid @RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRequestDto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> allProducts(){
        return ResponseEntity.ok(productService.listAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProdById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchProductByName(@RequestParam String name){
        return ResponseEntity.ok(productService.searchProductByName(name));
    }
    @GetMapping("/page")
    public ResponseEntity<List<ProductResponseDto>> getProductsByPage(
            @RequestParam int pageNum,
            @RequestParam int pageSize,
            @RequestParam List<String> sortFields,
            @RequestParam List<Sort.Direction> directions
    ){
        List<ProductResponseDto> products = productService.listProductsByPage
                (pageNum,pageSize,sortFields,directions);
        return ResponseEntity.ok(products);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Integer id, @RequestBody ProductRequestDto requestDto){
        return ResponseEntity.ok(productService.updateProduct(id,requestDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable Integer id){
        return ResponseEntity.ok(productService.searchProductByCategory(id));
    }


}
