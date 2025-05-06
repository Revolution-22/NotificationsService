package com.revolution.notification.service.infrastructure.email;

import com.revolution.notification.service.api.port.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.revolution.notification.service.infrastructure.Constants.FROM;

@Service
@Profile("production")
@RequiredArgsConstructor
class EmailServiceAdapter implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void send(final String to, final String subject, final String body) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(FROM);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
