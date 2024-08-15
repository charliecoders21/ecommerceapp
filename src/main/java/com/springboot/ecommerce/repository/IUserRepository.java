package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.models.UsersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<UsersDto,Long> {
    List<UsersDto> findByEmailId(String emailId);
    Optional<UsersDto> findByEmailIdOrMobileno(String emailId, Long mobileno);

}
