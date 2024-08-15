package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.models.CartEntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartEntityDto,Long> {

}
