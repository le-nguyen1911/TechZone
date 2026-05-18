package com.TechZone.TechZone_API.Service.Implement;

import com.TechZone.TechZone_API.Dto.Request.CategoryRequest;
import com.TechZone.TechZone_API.Entity.Category;
import com.TechZone.TechZone_API.Repository.CategoryRepository;
import com.TechZone.TechZone_API.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void create(CategoryRequest rq) {
        Category category = Category.builder().name(rq.getName()).build();
        categoryRepository.save(category);
        log.info(String.valueOf(category));
    }

    @Override
    public void update(String id, CategoryRequest rq) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new RuntimeException("categories not found");
        }
        Category categoryUD = category.get();
        categoryUD.setName(rq.getName());
        categoryRepository.save(categoryUD);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new RuntimeException("Categories not found");
        }
        categoryRepository.delete(category.get());
    }
}
