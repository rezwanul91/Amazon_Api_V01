package amazonApi_v01.services.service;

import amazonApi_v01.dto.brand.BrandRequestDto;
import amazonApi_v01.dto.brand.BrandResponseDto;

import java.util.List;

public interface BrandService {
    BrandResponseDto createBrand(BrandRequestDto brandRequestDto);
    List<BrandResponseDto> getAllBrand();
    BrandResponseDto getBrandById(Long id);
    BrandResponseDto updateBrandById(Long id, BrandRequestDto requestDto);
    void deleteBrandById(Long id);
}
