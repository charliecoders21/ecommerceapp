package com.springboot.ecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginResponseDto {
    private String message;
    private String token;
    private Long userId;
    private HttpStatus httpStatus;
}

