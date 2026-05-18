package com.TechZone.TechZone_API.Common;

public abstract class BaseController {
    protected <T> ApiResponse<T> createSuccessResponse(T data) {
        return ApiResponse.success(data);
    }
}
