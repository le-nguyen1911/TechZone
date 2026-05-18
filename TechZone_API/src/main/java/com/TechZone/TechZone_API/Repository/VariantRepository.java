package com.TechZone.TechZone_API.Repository;

import com.TechZone.TechZone_API.Entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<ProductVariant, String> {
}
