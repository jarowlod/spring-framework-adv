package pl.training.chat;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

import java.util.Map;

public class WebSocketUtils {

    public static String getSocketId(AbstractSubProtocolEvent event) {
        return stompHeaderAccessor(event).getSessionId();
    }

    public static Map<String, Object> getAttributes(AbstractSubProtocolEvent event) {
        return stompHeaderAccessor(event).getSessionAttributes();
    }

    private static StompHeaderAccessor stompHeaderAccessor(AbstractSubProtocolEvent event) {
        return StompHeaderAccessor.wrap(event.getMessage());
    }

}
