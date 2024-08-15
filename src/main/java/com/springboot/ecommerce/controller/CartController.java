package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.request.ProductCartRequestDto;
import com.springboot.ecommerce.response.CartResponseDto;
import com.springboot.ecommerce.service.ICartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
@RequiredArgsConstructor
public class CartController {
    private final ICartServices cartServices;
    @PostMapping("cart-add")
    public ResponseEntity<?> CartAddByBuyer(@RequestBody ProductCartRequestDto productCartRequestDto){
        CartResponseDto cartResponseDto = cartServices.PRODUCT_CART_REQUEST_DTO(productCartRequestDto);
        return new ResponseEntity<>(cartResponseDto,HttpStatus.OK);
    }



    @GetMapping("all-cart/{id}")
    public ResponseEntity<?> GetAllCart(@PathVariable("id") Long id){
        return new ResponseEntity<>(cartServices.CART_ENTITY_DTO_LIST(id),HttpStatus.OK);
    }
}
