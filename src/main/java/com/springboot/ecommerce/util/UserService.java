package com.springboot.ecommerce.util;

import com.springboot.ecommerce.exception.UsersNotFound;
import com.springboot.ecommerce.models.UsersDto;
import com.springboot.ecommerce.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final IUserRepository sellerRepository;
    /**
     * @param emailId

     */
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
      UsersDto usersDto= sellerRepository.findByEmailId(emailId).stream().findFirst().orElseThrow(()->new UsersNotFound("user is not found"));
      if(usersDto.getEmailId().equals(emailId)){
          return new User(usersDto.getEmailId(), usersDto.getPassword(), new ArrayList<>());
      }else {
          throw new UsersNotFound("User is not exist");
      }
    }
}
