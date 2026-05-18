package com.TechZone.TechZone_API.Repository.Specification;

import com.TechZone.TechZone_API.Entity.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> fillterProduct(String name, BigDecimal minPrice, BigDecimal maxPrice, String category, String brand) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Object, Object> variantJoin = root.join("variants");

            if (StringUtils.hasText(name)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicates.add((criteriaBuilder.greaterThanOrEqualTo(variantJoin.get("price"), minPrice)));
            }
            if (maxPrice != null) {
                predicates.add((criteriaBuilder.lessThanOrEqualTo(variantJoin.get("price"), maxPrice)));
            }
            if (StringUtils.hasText(brand)) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("brand").get("id"),
                                brand
                        )
                );
            }
            if (StringUtils.hasText(category)) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("category").get("id"),
                                category
                        )
                );
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
    }
}
