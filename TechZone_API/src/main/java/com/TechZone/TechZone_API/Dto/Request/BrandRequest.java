package com.TechZone.TechZone_API.Dto.Request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {
    @NotNull(message = "Trường này không được null")
    private String name;
}
