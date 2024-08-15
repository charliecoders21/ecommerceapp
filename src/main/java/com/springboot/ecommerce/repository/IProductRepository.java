package com.springboot.ecommerce.repository;

import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.models.ProductsDto;
import com.springboot.ecommerce.models.UsersDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductsDto,Long> {

}
