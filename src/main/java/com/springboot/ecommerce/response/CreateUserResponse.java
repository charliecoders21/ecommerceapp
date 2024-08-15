package com.springboot.ecommerce.response;

import com.springboot.ecommerce.request.UsersAuthRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateUserResponse {
    private String message;
    private UsersAuthRequestDto sellerRequestDto;
    private HttpStatus httpStatus;
}
