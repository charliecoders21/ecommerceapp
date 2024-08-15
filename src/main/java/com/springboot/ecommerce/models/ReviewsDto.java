package com.springboot.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "tbl_reviews")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    private String comment;
    private String reviewerName;
    private String reviewerEmail;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
