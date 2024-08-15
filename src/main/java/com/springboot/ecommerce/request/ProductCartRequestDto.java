package com.springboot.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCartRequestDto {
   private List<ItemCartRequestDto> itemCartRequestDto;
    private String emailId;
}
