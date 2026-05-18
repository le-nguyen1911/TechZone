package com.TechZone.TechZone_API.Service;

import com.TechZone.TechZone_API.Dto.Request.ProductRequest;
import com.TechZone.TechZone_API.Dto.Response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductResponse> index();

    Page<ProductResponse> getAllProduct(String name,
                                        BigDecimal minPrice,
                                        BigDecimal maxPrice,
                                        String category,
                                        String brand,
                                        Pageable pageable);

    ProductResponse getProductbyId(String id);

    ProductResponse create(ProductRequest rq);

    ProductResponse update(String id, ProductRequest rq);

    void delete(String id);

}
