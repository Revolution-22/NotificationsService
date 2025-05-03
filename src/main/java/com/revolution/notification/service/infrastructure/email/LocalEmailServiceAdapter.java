package com.revolution.notification.service.infrastructure.email;

import com.revolution.notification.service.api.port.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("!production")
class LocalEmailServiceAdapter implements EmailService {

    @Override
    public void send(final String to, final String subject, final String body) {
        log.info("Sending email to " + to);
        log.info("Subject: " + subject);
        log.info("Body: " + body);
    }
}
