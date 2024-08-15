package com.springboot.ecommerce.request;
import com.mysql.cj.protocol.Message;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsRequestDto{
    @NotEmpty(message="item name can't be null")
    private String itemName;
    @NotEmpty(message="item description can't be null")
    private String itemDescription;
    private int quantity;
    private double amount;
    private double discount;
    @NotEmpty(message="item imageUrl can't be null")
    private String imageUrl;
    @NotEmpty(message="item category can't be null")
    private String category;
    @NotNull(message="item status can't be null")
    private String itemStatus;
    @NotEmpty(message="item name can't be null")
    private String emailId;


}
