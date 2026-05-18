package com.TechZone.TechZone_API.Controller;

import com.TechZone.TechZone_API.Common.BaseController;
import com.TechZone.TechZone_API.Dto.Request.CategoryRequest;
import com.TechZone.TechZone_API.Entity.Category;
import com.TechZone.TechZone_API.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> showListCategory() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewCategory(@RequestBody CategoryRequest rq) {
        categoryService.create(rq);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") String id, @RequestBody CategoryRequest rq) {
        categoryService.update(id, rq);
        return new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") String id) {
        categoryService.delete(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
