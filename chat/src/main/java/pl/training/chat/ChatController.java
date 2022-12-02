package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Log
@RequiredArgsConstructor
public class ChatController {

    private static final String MAIN_ROOM = "/main-room";
    private static final String PRIVATE_ROOMS_PREFIX_ROOM = "/private-rooms/";

    private final TimeProvider timeProvider;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatEventEmitter chatEventEmitter;

    @MessageMapping("/chat")
    public void onMessage(@Payload ChatMessageDto chatMessageDto, @Header("simpSessionId") String simpSessionId, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        var attributes = simpMessageHeaderAccessor.getSessionAttributes();
        var user = attributes.get(simpSessionId);
        var clientId = attributes.get("clientId");

        var responseChatMessageDto = chatMessageDto.withTimestamp(timeProvider.getTime());
        log.info("New message "+ responseChatMessageDto);
        chatEventEmitter.emit(responseChatMessageDto);
        if (chatMessageDto.isForALl()) {
            messagingTemplate.convertAndSend(MAIN_ROOM, responseChatMessageDto);
        } else {
            messagingTemplate.convertAndSend(PRIVATE_ROOMS_PREFIX_ROOM + responseChatMessageDto.getSender(), responseChatMessageDto);
            chatMessageDto.getRecipients().forEach(recipient -> messagingTemplate.convertAndSend(PRIVATE_ROOMS_PREFIX_ROOM + recipient, responseChatMessageDto));
        }
    }

    /*@MessageMapping("/chat")
    @SendTo("/main-room")
    public ChatMessageDto onMessage(ChatMessageDto chatMessageDto) {
        var responseChatMessageDto = chatMessageDto.withTimestamp(timeProvider.getTime());
        log.info("New message "+ responseChatMessageDto);
        return responseChatMessageDto;
    }*/

}
