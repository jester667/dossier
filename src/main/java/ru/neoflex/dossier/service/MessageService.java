package ru.neoflex.dossier.service;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.neoflex.dossier.client.DealClient;
import ru.neoflex.dossier.model.ApplicationDTO;
import ru.neoflex.dossier.model.EmailMessageDTO;
import ru.neoflex.dossier.model.KafkaMessageDTO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    @Value("${custom.message.finish-registration.subject}")
    private String FINISH_REGISTRATION_SUBJECT;
    @Value("${custom.message.finish-registration.text}")
    private String FINISH_REGISTRATION_TEXT;
    @Value("${custom.message.create-document.subject}")
    private String CREATE_DOCUMENT_SUBJECT;
    @Value("${custom.message.create-document.text}")
    private String CREATE_DOCUMENT_TEXT;
    @Value("${custom.message.send-document.subject}")
    private String SEND_DOCUMENT_SUBJECT;
    @Value("${custom.message.send-document.text}")
    private String SEND_DOCUMENT_TEXT;
    @Value("${custom.message.send-ses.subject}")
    private String SEND_SES_SUBJECT;
    @Value("${custom.message.send-ses.text}")
    private String SEND_SES_TEXT;
    @Value("${custom.message.credit-issued.subject}")
    private String CREDIT_ISSUED_SUBJECT;
    @Value("${custom.message.credit-issued.text}")
    private String CREDIT_ISSUED_TEXT;
    @Value("${custom.message.application-denied.subject}")
    private String APPLICATION_DENIED_SUBJECT;
    @Value("${custom.message.application-denied.text}")
    private String APPLICATION_DENIED_TEXT;

    private final JavaMailSender javaMailSender;

    private final DealClient dealClient;

    public EmailMessageDTO kafkaMessageToEmailMessage(KafkaMessageDTO fromKafka) {
        String subject;
        String text;

        switch (fromKafka.getTheme()) {
            case FINISH_REGISTRATION: {
                ApplicationDTO application = dealClient.getApplicationById(fromKafka.getApplicationId()).getBody();
                String finishRegistrationTextEvaluated = FINISH_REGISTRATION_TEXT
                        .replaceAll("\\{applicationId\\}", application.getId().toString());
                subject = FINISH_REGISTRATION_SUBJECT;
                text = finishRegistrationTextEvaluated;
                break;
            }
            case CREATE_DOCUMENT: {
                ApplicationDTO application = dealClient.getApplicationById(fromKafka.getApplicationId()).getBody();
                String createDocumentTextEvaluated = CREATE_DOCUMENT_TEXT
                        .replaceAll("\\{applicationId\\}", application.getId().toString());
                subject = CREATE_DOCUMENT_SUBJECT;
                text = createDocumentTextEvaluated;
                break;
            }
            case SEND_DOCUMENT: {
                ApplicationDTO application = dealClient.getApplicationById(fromKafka.getApplicationId()).getBody();
                String sendDocumentTextEvaluated = SEND_DOCUMENT_TEXT.replaceAll("\\{applicationId\\}", application.getId().toString());
                subject = SEND_DOCUMENT_SUBJECT;
                text = sendDocumentTextEvaluated;
                break;
            }
            case SEND_SES: {
                ApplicationDTO application = dealClient.getApplicationById(fromKafka.getApplicationId()).getBody();
                String sesCode = application.getSesCode();
                String sendSesCodeTextEvaluated = SEND_SES_TEXT
                        .replaceAll("\\{sesCode\\}", sesCode)
                        .replaceAll("\\{applicationId\\}", application.getId().toString());
                subject = SEND_SES_SUBJECT;
                text = sendSesCodeTextEvaluated;
                break;
            }
            case CREDIT_ISSUED: {
                ApplicationDTO application = dealClient.getApplicationById(fromKafka.getApplicationId()).getBody();
                String sendDocumentTextEvaluated = CREDIT_ISSUED_TEXT.replaceAll("\\{applicationId\\}", application.getId().toString());
                subject = CREDIT_ISSUED_SUBJECT;
                text = sendDocumentTextEvaluated;
                break;
            }
            case APPLICATION_DENIED: {
                ApplicationDTO application = dealClient.getApplicationById(fromKafka.getApplicationId()).getBody();
                String sendDocumentTextEvaluated = APPLICATION_DENIED_TEXT.replaceAll("\\{applicationId\\}", application.getId().toString());
                subject = APPLICATION_DENIED_SUBJECT;
                text = sendDocumentTextEvaluated;
                break;
            }
            default: {
                throw new RuntimeException("Incorrect messageType");
            }
        }

        return EmailMessageDTO.builder()
                .address(fromKafka.getAddress())
                .address(subject)
                .text(text)
                .build();
    }

    public void sendMessage(String address, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jester677@yandex.ru");
        message.setTo(address);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendMessageWithAttachment(String address,
                                          String subject,
                                          String text,
                                          Map<String, File> attachmentsWithNames) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@conveyor.com");
            helper.setTo(address);
            helper.setSubject(subject);
            helper.setText(text);

            for (Map.Entry<String, File> entry: attachmentsWithNames.entrySet()) {
                helper.addAttachment(entry.getKey(), entry.getValue());
            }

            javaMailSender.send(message);

        } catch (MessagingException e) {
            log.warn("Some error during attaching files: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
