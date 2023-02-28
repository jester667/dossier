package ru.neoflex.dossier.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.neoflex.dossier.model.KafkaMessageDTO;

@Service
@AllArgsConstructor
public class JacksonUtilServiceImpl implements JacksonUtilService {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public KafkaMessageDTO convertToKafkaMessageDTO(String message) {
        return objectMapper.readValue(message, KafkaMessageDTO.class);
    }
}
