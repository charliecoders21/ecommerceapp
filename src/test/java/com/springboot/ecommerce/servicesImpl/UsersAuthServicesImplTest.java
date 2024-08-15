package com.springboot.ecommerce.servicesImpl;

import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.exception.UsersNotFound;
import com.springboot.ecommerce.models.UsersDto;
import com.springboot.ecommerce.repository.IUserRepository;
import com.springboot.ecommerce.request.UsersAuthRequestDto;
import com.springboot.ecommerce.response.LoginResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.xmlunit.util.Convert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersAuthServicesImplTest {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UsersAuthServicesImpl usersAuthServices;


    @Test
    public void createUserTest() {
        long mobileno = 981139321;
        long id = 9999;
        UsersAuthRequestDto usersDto = new UsersAuthRequestDto("chintan", "ngi", "chin.negi@hays.com", "chintan123@", mobileno, UsersRole.BUYER.toString());
        usersAuthServices.createUser(usersDto);
        UsersDto byEmailIdOrMobileno = userRepository.findByEmailIdOrMobileno("chin.negi@hays.com", mobileno)
                .orElseThrow(() -> new UsersNotFound("user is not found"));
        assertEquals("chin.negi@hays.com", byEmailIdOrMobileno.getEmailId());
    }
    @Test
    public void loginUserTest() {

        UsersAuthRequestDto usersDto = new UsersAuthRequestDto();
        usersDto.setEmailId("chin.negi@hays.com");
        usersDto.setPassword("chintan123@");
        long mobileno=981139321;
        usersDto.setMobileno(mobileno);
        LoginResponseDto loginResponseDto = usersAuthServices.LOGIN_RESPONSE_DTO(usersDto.getEmailId(), usersDto.getPassword(), usersDto.getMobileno());
        assertEquals(HttpStatus.OK,loginResponseDto.getHttpStatus());
    }
}