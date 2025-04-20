package com.example.Hello.Service;



import com.example.Hello.Mapper.BrandMapper;
import com.example.Hello.Repository.BrandRepository;
import com.example.Hello.dto.BrandDTO;
import com.example.Hello.entity.Brand;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = brandMapper.toEntity(brandDTO);
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        return brandMapper.toDTO(brandRepository.save(brand));
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setBrandName(brandDTO.getBrandName());
        brand.setLogoUrl(brandDTO.getLogoUrl());
        brand.setUpdatedAt(LocalDateTime.now());
        return brandMapper.toDTO(brandRepository.save(brand));
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public BrandDTO getBrandById(Long id) {
        return brandMapper.toDTO(brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found")));
    }

    public Page<BrandDTO> getBrands(Pageable pageable) {
        return brandRepository.findAll(pageable).map(brandMapper::toDTO);
    }
}

