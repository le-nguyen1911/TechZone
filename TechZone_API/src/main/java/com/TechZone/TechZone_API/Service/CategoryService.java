package com.TechZone.TechZone_API.Service;

import com.TechZone.TechZone_API.Dto.Request.CategoryRequest;
import com.TechZone.TechZone_API.Entity.Category;

import java.util.List;

public interface CategoryService {
    void create(CategoryRequest rq);

    void update(String id, CategoryRequest rq);

    List<Category> getAll();

    void delete(String id);
}
