package com.TechZone.TechZone_API.Common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
// dùng khi json trả ra bị null
public record ApiResponse<T>(int code, String mesage, T result) {
    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(1000, "success", result);
    }
    

    public static <T> ApiResponse<T> errorrs(int code, String message, T result) {
        return new ApiResponse<>(code, message, result);
    }

}
