package com.TechZone.TechZone_API.Controller;

import com.TechZone.TechZone_API.Common.ApiResponse;
import com.TechZone.TechZone_API.Common.BaseController;
import com.TechZone.TechZone_API.Dto.Request.ProductRequest;
import com.TechZone.TechZone_API.Dto.Response.ProductResponse;
import com.TechZone.TechZone_API.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController extends BaseController {
    private final ProductService productService;

//    @GetMapping
//    public ApiResponse<List<ProductResponse>> showListProduct() {
//        return createSuccessResponse(productService.index());
//    }

    @GetMapping
    ApiResponse<Page<ProductResponse>> index(
            @RequestParam(name = "pageNo", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "brand", required = false) String brand
    ) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page == 0 ? page : page - 1, size, sort);
        return ApiResponse.success(productService.getAllProduct(name,
                minPrice,
                maxPrice,
                category,
                brand,
                pageable));

    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getbyID(
            @PathVariable String id
    ) {

        ProductResponse product =
                productService.getProductbyId(id);

        return ApiResponse.success(product);
    }

    @PostMapping
    public ApiResponse<String> createProduct(@Valid @RequestBody ProductRequest rq) {
        productService.create(rq);
        return createSuccessResponse("create product successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest rq) {
        productService.update(id, rq);
        return createSuccessResponse("update successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable("id") String id) {
        productService.delete(id);
        return createSuccessResponse("delete successfully");

    }
}
