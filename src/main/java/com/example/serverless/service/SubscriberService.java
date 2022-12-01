package com.example.serverless.service;

import com.example.serverless.entity.EmailEntity;
import com.example.serverless.repo.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubscriberService {

    private final EmailRepository emailRepository;

    private final MailerService mailerService;

    private static final String NEWSLETTER_SUBJECT = "WEEKLY NEWSLETTER";

    public SubscriberService(EmailRepository emailRepository,
                             MailerService mailerService) {
        this.emailRepository = emailRepository;
        this.mailerService = mailerService;
    }

    @Bean
    public Consumer<String> subscribe() {
        return email-> {
            try {
                final EmailEntity emailEntity = emailRepository.save(new EmailEntity(email));
                log.info("subscribe request successful: {}", emailEntity);
            } catch (Exception exception) {
                log.error("subscribe request failed: {}", email, exception);
            }
        };
    }

    @Bean
    public Consumer<String> unsubscribe() {
        return email -> {
            try {
                final EmailEntity emailEntity = emailRepository.deleteByEmail(email);
                log.info("unsubscribe request successful: {}", emailEntity);
            } catch (Exception exception) {
                log.error("unsubscribe request failed: {}", email, exception);
            }
        };
    }

    @Bean
    public Consumer<String> send() {
        return message -> {
            final List<String> emails =
                    emailRepository.findAll().stream().map(EmailEntity::getEmail).collect(Collectors.toList());
            emails.forEach(email -> {
                try {
                    mailerService.sendTextMessage(email, NEWSLETTER_SUBJECT, message);
                } catch (Exception exception) {
                    log.error("Error sending news letter mail: {}", email, exception);
                }
            });
        };
    }
}
