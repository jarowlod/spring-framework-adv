package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static pl.training.chat.WebSocketUtils.getSocketId;

@Component
@Log
@RequiredArgsConstructor
public class WebSocketDisconnectedListener implements ApplicationListener<SessionDisconnectEvent> {

    private final SystemMessageSender systemMessageSender;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        var user = "Unknown";
        log.info("Socket with socket id %s disconnected (user: %s): ".formatted(getSocketId(event), user));
        systemMessageSender.send("User %s is now disconnected".formatted(user));
    }

}
