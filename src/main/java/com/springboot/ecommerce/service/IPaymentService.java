package com.springboot.ecommerce.service;

import com.springboot.ecommerce.request.OrderRequestDto;
import com.springboot.ecommerce.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface IPaymentService {
    PaymentResponse CreateOrder(OrderRequestDto order) throws StripeException;
}
