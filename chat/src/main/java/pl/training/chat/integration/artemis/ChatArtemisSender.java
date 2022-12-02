
package pl.training.chat.integration.artemis;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pl.training.chat.ChatEventEmitter;
import pl.training.chat.ChatMessageDto;

@Service
@RequiredArgsConstructor
public class ChatArtemisSender implements ChatEventEmitter {

    private final JmsTemplate jmsTemplate;

    @Value("${settings.default-topic}")
    @Setter
    private String topic;

    @Override
    public void emit(ChatMessageDto chatMessageDto) {
        //jmsTemplate.send(topic, session -> session.createObjectMessage(chatMessageDto));
        jmsTemplate.convertAndSend(topic, chatMessageDto);
    }

}
