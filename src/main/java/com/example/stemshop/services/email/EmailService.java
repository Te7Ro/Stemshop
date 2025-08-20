package com.example.stemshop.services.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOrderConfirmation(String to, Long orderId) {
        try{
            String subject = "Подтверждение заказа";
            String text = "Ваш заказ принят на обработку, номер заказа " + orderId;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            mailSender.send(message);

            log.info("Письмо с подтверждением заказа {} отправлено на {}", orderId, to);
        } catch (Exception e) {
            log.error("Ошибка при отправке письма пользователю {}", to, e);
        }
    }
}
