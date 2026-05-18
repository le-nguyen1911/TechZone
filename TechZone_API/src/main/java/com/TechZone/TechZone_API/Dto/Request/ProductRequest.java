package com.TechZone.TechZone_API.Dto.Request;

import com.TechZone.TechZone_API.Enum.ProductStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotEmpty(message = "Trường này không được để trống")
    private String name;
    @NotEmpty(message = "Trường này không được để trống")
    private String description;
    private ProductStatus status;
    @NotNull(message = "Trường này không được null")
    private String brandId;
    @NotNull(message = "Trường này không được null")
    private String categoryId;
    @Valid
    @NotEmpty(message = "Trường này không được để trống")
    private List<VariantRequest> variants;

}
