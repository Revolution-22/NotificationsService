package com.revolution.notification.service.infrastructure.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolution.common.event.EmailNotifyEvent;
import com.revolution.common.event.Topics;
import com.revolution.notification.service.api.port.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class EmailNotificationListener {

    private static final String GROUP_ID = "notification-service-group";

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @KafkaListener(topics = Topics.EMAIL_NOTIFICATION_TOPIC, groupId = GROUP_ID)
    void listen(String payload) throws JsonProcessingException {
        log.info("Received EmailNotifyEvent: {}", payload);
        EmailNotifyEvent event = objectMapper.readValue(payload, EmailNotifyEvent.class);
        emailService.send(event.email(), event.subject(), event.message());
    }
}
