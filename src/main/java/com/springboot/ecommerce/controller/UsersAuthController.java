package com.springboot.ecommerce.controller;
import com.springboot.ecommerce.request.UsersAuthRequestDto;
import com.springboot.ecommerce.response.CreateUserResponse;
import com.springboot.ecommerce.response.LoginResponseDto;
import com.springboot.ecommerce.service.IUsersAuth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/")
//@Tag(name="UsersAuthController")
public class UsersAuthController {
    private final IUsersAuth usersAuth;
    //@Operation(summary = "Post Method to insert the records of user")
    @PostMapping("register")
    public ResponseEntity<?> Register(@RequestBody UsersAuthRequestDto usersAuthRequestDto){
        try{
            CreateUserResponse sellerServiceUser = usersAuth.createUser(usersAuthRequestDto);
        if(sellerServiceUser.getHttpStatus().equals(HttpStatus.CREATED)){
            return new ResponseEntity<>(sellerServiceUser,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(sellerServiceUser,HttpStatus.BAD_REQUEST);
        }}
        catch (Exception exception){
            throw exception;
        }
    }
    //@Operation(summary = "Post Method to Login for the authorize user")
    @PostMapping("login")
    public ResponseEntity<?> Login(@RequestBody UsersAuthRequestDto usersAuthRequestDto){
        LoginResponseDto sellerServiceUser = usersAuth.LOGIN_RESPONSE_DTO(usersAuthRequestDto.getEmailId(), usersAuthRequestDto.getPassword(), usersAuthRequestDto.getMobileno());
        if(sellerServiceUser.getHttpStatus().equals(HttpStatus.OK)){
            return new ResponseEntity<>(sellerServiceUser,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(sellerServiceUser,HttpStatus.FORBIDDEN);
        }
    }
}
