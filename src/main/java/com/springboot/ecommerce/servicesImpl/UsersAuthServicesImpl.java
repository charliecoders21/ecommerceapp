package com.springboot.ecommerce.servicesImpl;


import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.exception.UsersNotFound;
import com.springboot.ecommerce.models.UsersDto;
import com.springboot.ecommerce.models.UsersDto;
import com.springboot.ecommerce.repository.IUserRepository;
import com.springboot.ecommerce.request.UsersAuthRequestDto;
import com.springboot.ecommerce.response.CreateUserResponse;
import com.springboot.ecommerce.response.LoginResponseDto;
import com.springboot.ecommerce.service.IUsersAuth;
import com.springboot.ecommerce.util.JwtHelper;
import com.springboot.ecommerce.util.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersAuthServicesImpl implements IUsersAuth {
    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final AuthenticationManager manager;
    /**
     * @param userRequestDto
     * @return
     */
    @Override
    @Transactional
    public CreateUserResponse createUser(UsersAuthRequestDto userRequestDto) {
        try {
            boolean isPresent = userRepository.findByEmailIdOrMobileno(userRequestDto.getEmailId(), userRequestDto.getMobileno()).isEmpty();
            if (isPresent) {
                userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

                UsersDto usersDto = UsersDtoMap(userRequestDto);
                userRepository.save(usersDto);
                return new CreateUserResponse(userRequestDto.getUsersRole()+"profile is created successfully", userRequestDto, HttpStatus.CREATED);
            } else {
                return new CreateUserResponse(userRequestDto.getUsersRole()+"profile is already exist with this email/mobile please try again with different email id", userRequestDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception exception){
            throw exception;
        }
    }

    /**
     * @param emailId
     * @param password
     * @param mobileno
     * @return
     */
    @Override
    public LoginResponseDto LOGIN_RESPONSE_DTO(String emailId, String password, Long mobileno) {
       doAuthenticate(emailId,password);
        UsersDto UsersDto= userRepository.findByEmailIdOrMobileno(emailId,mobileno).orElseThrow(()->new UsersNotFound("user are nor exist"));
        boolean passwordMatch = passwordEncoder.matches(password, UsersDto.getPassword());
        if(UsersDto.getEmailId().equalsIgnoreCase(emailId) && passwordMatch){
            UserDetails userDetails = userService.loadUserByUsername(UsersDto.getEmailId());
            String token = jwtHelper.generateToken(userDetails);
            return new LoginResponseDto("Login Successfully",token, UsersDto.getId(), HttpStatus.OK);
        }else {
            return new LoginResponseDto("Invalid username/password","", UsersDto.getId(), HttpStatus.FORBIDDEN);
        }
    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    private UsersDto UsersDtoMap(UsersAuthRequestDto usersAuthRequestDto){
        UsersDto UsersDto=new UsersDto();
        UsersDto.setFirstName(usersAuthRequestDto.getFirstName());
        UsersDto.setLastName(usersAuthRequestDto.getLastName());
        UsersDto.setEmailId(usersAuthRequestDto.getEmailId());
        UsersDto.setPassword(usersAuthRequestDto.getPassword());
        UsersDto.setMobileno(usersAuthRequestDto.getMobileno());
        if (usersAuthRequestDto.getUsersRole().equalsIgnoreCase(UsersRole.SELLER.toString())) {
            UsersDto.setUsersRole(UsersRole.SELLER);
        } else if (usersAuthRequestDto.getUsersRole().equalsIgnoreCase(UsersRole.ADMIN.toString())) {
            UsersDto.setUsersRole(UsersRole.ADMIN);
        } else {
            UsersDto.setUsersRole(UsersRole.BUYER);
        }

        return UsersDto;
    }

}
