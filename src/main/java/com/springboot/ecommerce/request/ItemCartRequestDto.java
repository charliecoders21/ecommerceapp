package com.springboot.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCartRequestDto {
    private String itemName;
    private String itemDescription;
    private int quantity;
    private double amount;


}
