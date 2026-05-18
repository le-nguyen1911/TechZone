package com.TechZone.TechZone_API.Dto.Response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariantResponse {
    private String id;
    private String color;
    private String storage;
    private BigDecimal price;
    private String imageUrl;
    private Integer stock;
}
