package amazonApi_v01.controller;

import amazonApi_v01.dto.brand.BrandRequestDto;
import amazonApi_v01.dto.brand.BrandResponseDto;
import amazonApi_v01.dto.category.CategoryResponseDto;
import amazonApi_v01.entity.Brand;
import amazonApi_v01.entity.Category;
import amazonApi_v01.services.service.BrandService;
import amazonApi_v01.services.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<BrandResponseDto> createBrand(@RequestBody BrandRequestDto requestDto){
        BrandResponseDto brandResponseDto = brandService.createBrand(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponseDto);
    }
    @GetMapping("/{brandId}/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategoryByBrandId(@PathVariable Long brandId){
        List<CategoryResponseDto> categories = categoryService.getCategoryByBrandId(brandId);
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<BrandResponseDto>> getAllBrands(){
        return ResponseEntity.ok(brandService.getAllBrand());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDto> getBrandByIds(@PathVariable Long id){
        return ResponseEntity.ok(brandService.getBrandById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBrandByIds(@PathVariable Long id,
                                                             @RequestBody BrandRequestDto requestDto){
        return ResponseEntity.ok(brandService.updateBrandById(id,requestDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrands(@PathVariable Long id){
        brandService.deleteBrandById(id);
       return ResponseEntity.noContent().build();
    }
}
