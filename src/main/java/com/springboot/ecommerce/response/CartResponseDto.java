package com.springboot.ecommerce.response;

import com.springboot.ecommerce.request.ProductCartRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CartResponseDto {
    private String message;
    private HttpStatus httpStatus;
    private ProductCartRequestDto productCartRequestDto;
}
