package ru.neoflex.dossier.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.neoflex.dossier.client.DealClient;
import ru.neoflex.dossier.model.ApplicationStatus;
import ru.neoflex.dossier.service.DocumentService;
import ru.neoflex.dossier.service.MessageService;
import ru.neoflex.dossier.service.common.JacksonUtilService;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class KafkaConsumer {

    private final JacksonUtilService jacksonUtil;
    private final MessageService messageService;
    private final DocumentService documentService;
    private final DealClient dealClient;

    @KafkaListener(topics = "${spring.kafka.topic.finish-registration}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeFinishRegistration(String message) {
        var messageDto = jacksonUtil.convertToKafkaMessageDTO(message);
        var emailMessage = messageService.kafkaMessageToEmailMessage(messageDto);
        messageService.sendMessage(emailMessage.getAddress(), emailMessage.getSubject(),  emailMessage.getText());
    }

    @KafkaListener(topics = "${spring.kafka.topic.create-documents}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeCreateDocuments(String message) {
        var messageDto = jacksonUtil.convertToKafkaMessageDTO(message);
        var emailMessage = messageService.kafkaMessageToEmailMessage(messageDto);
        messageService.sendMessage(emailMessage.getAddress(), emailMessage.getSubject(),  emailMessage.getText());    }

    @KafkaListener(topics = "${spring.kafka.topic.send-documents}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeSendDocuments(String message) {
        var messageDto = jacksonUtil.convertToKafkaMessageDTO(message);
        var emailMessage = messageService.kafkaMessageToEmailMessage(messageDto);
        List<File> documents = documentService.createDocuments(messageDto.getApplicationId());
        Map<String, File> documentsWithNames = documents.stream()
                .collect(Collectors.toMap(File::getName, file -> file));

        dealClient.updateApplicationStatusById(messageDto.getApplicationId(), ApplicationStatus.DOCUMENT_CREATED.name());

        messageService.sendMessageWithAttachment(emailMessage.getAddress(),
                emailMessage.getSubject(),
                emailMessage.getText(),
                documentsWithNames);
    }

    @KafkaListener(topics = "${spring.kafka.topic.send-ses}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeSendSes(String message) {
        var messageDto = jacksonUtil.convertToKafkaMessageDTO(message);
        var emailMessage = messageService.kafkaMessageToEmailMessage(messageDto);
        messageService.sendMessage(emailMessage.getAddress(), emailMessage.getSubject(),  emailMessage.getText());    }

    @KafkaListener(topics = "${spring.kafka.topic.credit-issued}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeCreditIssued(String message) {
        var messageDto = jacksonUtil.convertToKafkaMessageDTO(message);
        var emailMessage = messageService.kafkaMessageToEmailMessage(messageDto);
        messageService.sendMessage(emailMessage.getAddress(), emailMessage.getSubject(),  emailMessage.getText());    }

    @KafkaListener(topics = "${spring.kafka.topic.application-denied}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeApplicationDenied(String message) {
        var messageDto = jacksonUtil.convertToKafkaMessageDTO(message);
        var emailMessage = messageService.kafkaMessageToEmailMessage(messageDto);
        messageService.sendMessage(emailMessage.getAddress(), emailMessage.getSubject(),  emailMessage.getText());    }
}
