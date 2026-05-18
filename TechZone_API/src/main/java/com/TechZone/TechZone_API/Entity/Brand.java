package com.TechZone.TechZone_API.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brands")
@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
}
