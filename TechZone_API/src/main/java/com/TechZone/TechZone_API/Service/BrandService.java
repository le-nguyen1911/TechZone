package com.TechZone.TechZone_API.Service;

import com.TechZone.TechZone_API.Dto.Request.BrandRequest;
import com.TechZone.TechZone_API.Entity.Brand;

import java.util.List;

public interface BrandService {

    void create(BrandRequest rq);

    void update(String id, BrandRequest rq);

    List<Brand> getAll();

    void delete(String id);
}
