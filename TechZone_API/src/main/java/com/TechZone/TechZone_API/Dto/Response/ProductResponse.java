package com.TechZone.TechZone_API.Dto.Response;

import com.TechZone.TechZone_API.Enum.ProductStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private ProductStatus status;
    private String brandId;
    private String categoryId;
    private List<VariantResponse> variants;
}
