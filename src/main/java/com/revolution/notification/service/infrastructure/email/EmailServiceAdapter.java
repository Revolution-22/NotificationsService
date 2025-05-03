package com.revolution.notification.service.infrastructure.email;

import com.revolution.notification.service.api.port.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.revolution.notification.service.infrastructure.Constants.FROM;

@Service
@Profile("production")
@RequiredArgsConstructor
class EmailServiceAdapter implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void send(final String to, final String subject, final String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
