package com.springboot.ecommerce.service;

import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.request.UsersAuthRequestDto;
import com.springboot.ecommerce.response.CreateUserResponse;
import com.springboot.ecommerce.response.LoginResponseDto;

public interface IUsersAuth {
    CreateUserResponse createUser(UsersAuthRequestDto userRequestDto);
    LoginResponseDto LOGIN_RESPONSE_DTO(String emailId, String password, Long mobileno);
}
