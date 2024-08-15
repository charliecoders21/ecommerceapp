package com.springboot.ecommerce.servicesImpl;

import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.exception.UsersNotFound;
import com.springboot.ecommerce.models.ProductsDto;
import com.springboot.ecommerce.models.UsersDto;
import com.springboot.ecommerce.repository.IProductRepository;
import com.springboot.ecommerce.repository.IUserRepository;
import com.springboot.ecommerce.request.ProductsRequestDto;
import com.springboot.ecommerce.response.ProductDtoResponse;
import com.springboot.ecommerce.service.IProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServicesImpl implements IProductsService {
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;
    /**
     * @param productsRequestDto
     * @return
     */
    @Override
    public ProductDtoResponse productAddResponse(ProductsRequestDto productsRequestDto) {
        UsersDto findByEmailId = userRepository.findByEmailId(productsRequestDto.getEmailId())
                .stream().filter(x->x.getUsersRole()==UsersRole.ADMIN).findFirst()
                .orElseThrow(()->new UsersNotFound("user is not found or it's not the admin role"));
        ProductsDto productsDto =ProductDtoMap(productsRequestDto);
        productsDto.setUpdatedById(findByEmailId.getId());
        productRepository.save(productsDto);
        return new ProductDtoResponse("product is saved successfully", HttpStatus.CREATED);
    }

    /**
     * @return
     */
    @Override
    public List<ProductsDto> productGetAllData() {
        return productRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductsDto productGetByIdData(Long id) {
        return productRepository.findById(id).get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductDtoResponse productDeleteByIdData(Long id) {
        productRepository.deleteById(id);
        return new ProductDtoResponse("Product is successfully deleted",HttpStatus.OK);
    }

    /**
     * @param productsRequestDto
     * @param id
     * @return
     */
    @Override
    public ProductDtoResponse productUpdateData(ProductsRequestDto productsRequestDto, Long id) {
        ProductsDto data = productRepository.findById(id).orElseThrow(()->new UsersNotFound("product is not found"));
        data = ProductDtoUpdateMap(productsRequestDto, data);
        productRepository.save(data);
        return new ProductDtoResponse("Product is updated successfully",HttpStatus.OK);
    }

    private ProductsDto ProductDtoMap(ProductsRequestDto productsRequestDto){
        ProductsDto productsDto=new ProductsDto();
        productsDto.setItemName(productsRequestDto.getItemName());
        productsDto.setItemDescription(productsRequestDto.getItemDescription());
        productsDto.setQuantity(productsRequestDto.getQuantity());
        productsDto.setAmount(productsRequestDto.getAmount());
        productsDto.setDiscount(productsRequestDto.getDiscount());
        productsDto.setImageUrl(productsRequestDto.getImageUrl());
        productsDto.setCategory(productsRequestDto.getCategory());
        productsDto.setItemStatus(productsRequestDto.getItemStatus());
        productsDto.setEmailId(productsDto.getEmailId());
        return productsDto;
    }
    private ProductsDto ProductDtoUpdateMap(ProductsRequestDto productsRequestDto,ProductsDto data){

        data.setItemName(productsRequestDto.getItemName());
        data.setItemDescription(productsRequestDto.getItemDescription());
        data.setQuantity(productsRequestDto.getQuantity());
        data.setAmount(productsRequestDto.getAmount());
        data.setDiscount(productsRequestDto.getDiscount());
        data.setImageUrl(productsRequestDto.getImageUrl());
        data.setCategory(productsRequestDto.getCategory());
        data.setItemStatus(productsRequestDto.getItemStatus());
        data.setEmailId(productsRequestDto.getEmailId());
        return data;
    }
}
