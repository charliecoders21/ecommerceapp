package com.springboot.ecommerce.service;

import com.springboot.ecommerce.models.CartEntityDto;
import com.springboot.ecommerce.request.ProductCartRequestDto;
import com.springboot.ecommerce.response.CartResponseDto;

import java.util.List;

public interface ICartServices {
    CartResponseDto PRODUCT_CART_REQUEST_DTO(ProductCartRequestDto productsRequestDto);
    List<CartEntityDto> CART_ENTITY_DTO_LIST(Long id);
}
