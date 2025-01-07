package amazonApi_v01.controller;

import amazonApi_v01.dto.category.CategoryRequestDto;
import amazonApi_v01.dto.category.CategoryResponseDto;
import amazonApi_v01.entity.Category;
import amazonApi_v01.services.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

@PostMapping("/add")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequestDto requestDto){
        CategoryResponseDto saveCategory = categoryService.createCategory(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
       List<CategoryResponseDto> categoryResponseDtos = categoryService.getAllCategories();
       return ResponseEntity.ok(categoryResponseDtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDto requestDto){
        CategoryResponseDto responseDto = categoryService.updateCategory(id,requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        CategoryResponseDto responseDto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
