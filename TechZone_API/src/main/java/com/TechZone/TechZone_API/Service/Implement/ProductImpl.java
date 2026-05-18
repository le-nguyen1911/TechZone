package com.TechZone.TechZone_API.Service.Implement;

import com.TechZone.TechZone_API.Dto.Request.ProductRequest;
import com.TechZone.TechZone_API.Dto.Response.ProductResponse;
import com.TechZone.TechZone_API.Entity.Brand;
import com.TechZone.TechZone_API.Entity.Category;
import com.TechZone.TechZone_API.Entity.Product;
import com.TechZone.TechZone_API.Entity.ProductVariant;
import com.TechZone.TechZone_API.Mapper.ProductMapper;
import com.TechZone.TechZone_API.Repository.BrandRepository;
import com.TechZone.TechZone_API.Repository.CategoryRepository;
import com.TechZone.TechZone_API.Repository.ProductRepository;
import com.TechZone.TechZone_API.Repository.Specification.ProductSpecification;
import com.TechZone.TechZone_API.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> index() {
        return productRepository.findAll().stream().map(productMapper::mapToResponse).toList();
    }

    @Override
    public Page<ProductResponse> getAllProduct(
            String name,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String category,
            String brand,
            Pageable pageable
    ) {

        Page<Product> products =
                productRepository.findAll(
                        ProductSpecification.fillterProduct(
                                name,
                                minPrice,
                                maxPrice,
                                category,
                                brand
                        ),
                        pageable
                );

        return products.map(productMapper::mapToResponse);
    }

    @Override
    public ProductResponse getProductbyId(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("product not found"));
        return productMapper.mapToResponse(product);
    }


    @Override
    public ProductResponse create(ProductRequest rq) {

        //Lấy brand từ database
        Brand brand = brandRepository.findById(rq.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        // lấy category từ database
        Category category = categoryRepository.findById(rq.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        //tạo product
        Product product = Product.builder()
                .name(rq.getName())
                .description(rq.getDescription())
                .brand(brand)
                .category(category).build();

        //tạo variant
        List<ProductVariant> variants = rq.getVariants().stream()
                .map(v -> ProductVariant.builder()
                        .color(v.getColor())
                        .storage(v.getStorage())
                        .price(v.getPrice())
                        .stock(v.getStock())
                        .imageUrl(v.getImageUrl())
                        .product(product).build()).toList();
        //khi tạo xong variant thì phỉa set variant
        product.setVariants(variants);
        Product productsaved = productRepository.save(product);

        return productMapper.mapToResponse(productsaved);
    }

    @Override
    public ProductResponse update(String id, ProductRequest rq) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productMapper.updateProduct(product, rq);

        if (rq.getBrandId() != null) {
            Brand brand = brandRepository.findById(rq.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
            product.setBrand(brand);
        }

        if (rq.getCategoryId() != null) {
            Category category = categoryRepository.findById(rq.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }


        if (rq.getVariants() != null) {
            // clear variants cũ
            product.getVariants().clear();

            // add variants mới
            List<ProductVariant> variants = rq.getVariants().stream()
                    .map(v -> ProductVariant.builder()
                            .color(v.getColor())
                            .storage(v.getStorage())
                            .price(v.getPrice())
                            .stock(v.getStock())
                            .imageUrl(v.getImageUrl())
                            .product(product)
                            .build())
                    .toList();

            product.getVariants().addAll(variants);
        }

        Product saved = productRepository.save(product);

        return productMapper.mapToResponse(saved);
    }

    @Override
    public void delete(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        productRepository.delete(product.get());
    }
}
