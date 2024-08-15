package com.springboot.ecommerce.service;

import com.springboot.ecommerce.models.ProductsDto;
import com.springboot.ecommerce.request.ProductsRequestDto;
import com.springboot.ecommerce.response.ProductDtoResponse;

import java.util.List;

public interface IProductsService {
    ProductDtoResponse productAddResponse(ProductsRequestDto productsRequestDto);
    List<ProductsDto> productGetAllData();
    ProductsDto productGetByIdData(Long id);
    ProductDtoResponse productDeleteByIdData(Long id);
    ProductDtoResponse productUpdateData(ProductsRequestDto productsRequestDto,Long id);
}
