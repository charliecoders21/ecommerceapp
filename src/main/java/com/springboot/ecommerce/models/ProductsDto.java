package com.springboot.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "tbl_products")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsDto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String itemDescription;
    private int quantity;
    private double amount;
    private double discount;
    private String imageUrl;
    private String category;
    private String itemStatus;
    private String emailId;
    private Long updatedById;

}
