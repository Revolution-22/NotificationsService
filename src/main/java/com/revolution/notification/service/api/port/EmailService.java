package com.revolution.notification.service.api.port;

public interface EmailService {

    void send(String to, String subject, String body);
}
