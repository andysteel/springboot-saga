package com.gmail.andersoninfonet.order.api.model.request;

public record OrderRequest(String productId, String userId, String addressId, int quantity) {
    
}
