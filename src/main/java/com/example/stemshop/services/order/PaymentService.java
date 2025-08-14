package com.example.stemshop.services.order;

import com.example.stemshop.data.enums.PaymentMethod;
import com.example.stemshop.data.enums.PaymentStatus;
import com.example.stemshop.dto.response.order.PaymentResponse;
import com.example.stemshop.exceptions.NotFoundException;
import com.example.stemshop.models.Order;
import com.example.stemshop.models.Payment;
import com.example.stemshop.repositories.PaymentRepository;
import com.example.stemshop.util.PaymentMapper;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public String createCheckoutSession(Order order) throws StripeException {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentMethod(PaymentMethod.CARD);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);


        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(""+order.getId())
                .setCancelUrl(""+order.getId())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("kzt")
                                                .setUnitAmount((long) order.getTotalPrice() * 100) // в тиынах
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Оплата заказа №" + order.getId())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        Session session = Session.create(params);
        return session.getUrl();

    }

    public PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus) {
        final Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Транзакция не найдена"));

        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);

        return paymentMapper.toResponse(payment);
    }

}
