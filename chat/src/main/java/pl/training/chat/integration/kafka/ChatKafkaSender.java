package pl.training.chat.integration.kafka;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.training.chat.ChatMessageDto;
import pl.training.chat.ChatEventEmitter;

@Service
@RequiredArgsConstructor
public class ChatKafkaSender implements ChatEventEmitter {

    private final KafkaTemplate<String, ChatMessageDto> kafkaTemplate;

    @Value("${settings.default-topic}")
    @Setter
    private String topic;

    @Override
    public void emit(ChatMessageDto chatMessageDto) {
        kafkaTemplate.send(topic, chatMessageDto);
    }

}
