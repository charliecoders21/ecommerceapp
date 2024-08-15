package com.springboot.ecommerce.controller;

import com.springboot.ecommerce.request.OrderRequestDto;
import com.springboot.ecommerce.response.PaymentResponse;
import com.springboot.ecommerce.service.IPaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentService paymentService;
    @PostMapping("/order")
    public ResponseEntity<?> CreateOrder(@RequestBody OrderRequestDto orderRequestDto) throws StripeException {
        PaymentResponse paymentResponse = paymentService.CreateOrder(orderRequestDto);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);

    }
}
