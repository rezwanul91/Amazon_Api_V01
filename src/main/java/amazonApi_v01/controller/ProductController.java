package amazonApi_v01.controller;

import amazonApi_v01.dto.product.ProductRequestDto;
import amazonApi_v01.dto.product.ProductResponseDto;
import amazonApi_v01.services.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
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

}
