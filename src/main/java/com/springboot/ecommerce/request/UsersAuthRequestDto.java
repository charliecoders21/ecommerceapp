package com.springboot.ecommerce.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersAuthRequestDto {
    @NotEmpty(message = "first name can't be null")
    private String firstName;
    @NotEmpty(message = "last name can't be null")
    private String lastName;
    @NotEmpty(message = "email id can't be null")
    private String emailId;
    @NotEmpty(message = "password can't be null")
    private String password;
    @NotEmpty(message = "mobile number can't be null")
    private Long mobileno;
    private String usersRole;
}
