package com.TechZone.TechZone_API.Mapper;

import com.TechZone.TechZone_API.Dto.Request.ProductRequest;
import com.TechZone.TechZone_API.Dto.Response.ProductResponse;
import com.TechZone.TechZone_API.Entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse mapToResponse(Product product);

    //field nào null thì bỏ qua ko null thì update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(
            @MappingTarget Product product, ProductRequest rq
    );
}
