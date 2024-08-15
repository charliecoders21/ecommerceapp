package com.springboot.ecommerce.servicesImpl;

import com.springboot.ecommerce.request.OrderRequestDto;
import com.springboot.ecommerce.response.PaymentResponse;
import com.springboot.ecommerce.service.IPaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServicesImpl implements IPaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    /**
     * @param order
     * @return
     */
    @Override
    public PaymentResponse CreateOrder(OrderRequestDto order) throws StripeException {
        Stripe.apiKey = stripeApiKey;
        SessionCreateParams params = SessionCreateParams.
                builder().
                addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success" + order.getId())
                .setCancelUrl("http://localhost:3000/payment/failed")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) order.getTotalAmount() * 100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("negi-ecoomerce").build()).build()
                        ).build()).build();
        Session session = Session.create(params);

        return new PaymentResponse(session.getUrl());
    }
}
