package ru.neoflex.dossier.service.common;

import ru.neoflex.dossier.model.KafkaMessageDTO;

public interface JacksonUtilService {

    KafkaMessageDTO convertToKafkaMessageDTO(String message);

}
