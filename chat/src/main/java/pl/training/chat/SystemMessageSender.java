package pl.training.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemMessageSender {

    private static final String MAIN_ROOM = "/main-room";
    private static final String SYSTEM_SENDER = "System";

    private final SimpMessagingTemplate messagingTemplate;
    private final TimeProvider timeProvider;

    public void send(String text) {
        var message = ChatMessageDto.builder()
                .sender(SYSTEM_SENDER)
                .text(text)
                .timestamp(timeProvider.getTime())
                .build();
        messagingTemplate.convertAndSend(MAIN_ROOM, message);
    }

}
