package com.TechZone.TechZone_API.Controller;

import com.TechZone.TechZone_API.Dto.Request.BrandRequest;
import com.TechZone.TechZone_API.Entity.Brand;
import com.TechZone.TechZone_API.Service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> showListBrand() {
        return new ResponseEntity<>(brandService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creatNewBrand(@RequestBody BrandRequest rq) {
        brandService.create(rq);
        return new ResponseEntity<>("Create a new brand successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBrand(@PathVariable("id") String id, @RequestBody BrandRequest rq) {
        brandService.update(id, rq);
        return new ResponseEntity<>("update successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable("id") String id) {
        brandService.delete(id);
        return new ResponseEntity<>("delete brand successfully", HttpStatus.OK);
    }
}

