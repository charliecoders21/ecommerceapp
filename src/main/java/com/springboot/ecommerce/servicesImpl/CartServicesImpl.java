package com.springboot.ecommerce.servicesImpl;

import com.springboot.ecommerce.enums.UsersRole;
import com.springboot.ecommerce.exception.UsersNotFound;
import com.springboot.ecommerce.models.CartEntityDto;
import com.springboot.ecommerce.models.UsersDto;
import com.springboot.ecommerce.repository.ICartRepository;
import com.springboot.ecommerce.repository.IUserRepository;
import com.springboot.ecommerce.request.ItemCartRequestDto;
import com.springboot.ecommerce.request.ProductCartRequestDto;
import com.springboot.ecommerce.request.ProductsRequestDto;
import com.springboot.ecommerce.response.CartResponseDto;
import com.springboot.ecommerce.service.ICartServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CartServicesImpl implements ICartServices {
    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;
    /**
     * @param productsRequestDto
     * @return
     */
    @Override
    public CartResponseDto PRODUCT_CART_REQUEST_DTO(ProductCartRequestDto productsRequestDto) {
        UsersDto findByEmailId = userRepository.findByEmailId(productsRequestDto.getEmailId())
                .stream().filter(x->x.getUsersRole()== UsersRole.BUYER).findFirst().orElseThrow(()->
                        new UsersNotFound("user is not found by this role"));
        List<CartEntityDto> collect = productsRequestDto.getItemCartRequestDto()
                .stream()
                .map(cart ->
                        new CartEntityDto(
                                cart.getItemName(),
                                cart.getItemDescription(),
                                cart.getQuantity(),
                                cart.getAmount(),
                                findByEmailId.getId()
                        )).collect(toList());
        cartRepository.saveAll(collect);
         return new CartResponseDto("cart is added successfully", HttpStatus.OK,productsRequestDto);
    }

    /**
     * @return
     */
    @Override
    public List<CartEntityDto> CART_ENTITY_DTO_LIST(Long id) {
        return cartRepository.findAll().stream()
                .filter(x->x.getUserId().equals(id)).collect(toList());
    }

}
