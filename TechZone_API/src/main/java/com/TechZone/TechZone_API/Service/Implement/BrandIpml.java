package com.TechZone.TechZone_API.Service.Implement;

import com.TechZone.TechZone_API.Dto.Request.BrandRequest;
import com.TechZone.TechZone_API.Entity.Brand;
import com.TechZone.TechZone_API.Repository.BrandRepository;
import com.TechZone.TechZone_API.Service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandIpml implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public void create(BrandRequest rq) {
        brandRepository.save(Brand.builder().name(rq.getName()).build());
    }

    @Override
    public void update(String id, BrandRequest rq) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isEmpty()) {
            throw new RuntimeException("Brand not found");
        }
        Brand brandupdate = brand.get();
        brandupdate.setName(rq.getName());
        brandRepository.save(brandupdate);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isEmpty()) {
            throw new RuntimeException("Brand not found");
        }
        brandRepository.delete(brand.get());
    }
}
