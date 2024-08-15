package com.springboot.ecommerce.request;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderRequestDto {

    private Long id;
    private int totalAmount;
}
