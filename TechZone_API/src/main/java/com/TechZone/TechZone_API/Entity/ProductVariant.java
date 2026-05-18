package com.TechZone.TechZone_API.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product_variants")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String color;
    private String storage;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;
    private String imageUrl;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
