package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.request.ProductsRequestDto;
import com.springboot.ecommerce.response.ProductDtoResponse;
import com.springboot.ecommerce.service.IProductsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/")
public class ProductController {
    private final IProductsService productsService;
    @PostMapping("create-product")
    public ResponseEntity<?> CreateProductBYAdmin(@Valid @RequestBody ProductsRequestDto productsRequestDto) {
        ProductDtoResponse productDtoResponse = productsService.productAddResponse(productsRequestDto);
        if(productDtoResponse.getHttpStatus().equals(HttpStatus.CREATED)){
            return new ResponseEntity<>(productDtoResponse,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("update-product/{id}")
    public ResponseEntity<?> UpdateProductBYAdmin(@RequestBody ProductsRequestDto productsRequestDto, @PathVariable("id") Long id) {
        ProductDtoResponse productDtoResponse = productsService.productUpdateData(productsRequestDto,id);
        if(productDtoResponse.getHttpStatus().equals(HttpStatus.OK)){
            return new ResponseEntity<>(productDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping()
    public ResponseEntity<?> GetAllProducts(){
        return new ResponseEntity<>(productsService.productGetAllData(),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> GetProductsById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productsService.productGetByIdData(id),HttpStatus.OK);
    }
}

