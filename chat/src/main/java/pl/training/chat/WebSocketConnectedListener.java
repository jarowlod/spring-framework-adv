package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import static pl.training.chat.WebSocketUtils.getNativeHeader;
import static pl.training.chat.WebSocketUtils.getSocketId;

@Component
@Log
@RequiredArgsConstructor
public class WebSocketConnectedListener implements ApplicationListener<SessionConnectedEvent> {

    private static final String USER_HEADER = "user";
    private final SystemMessageSender systemMessageSender;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        var user = getNativeHeader(event, USER_HEADER);
        log.info("Socket with socket id %s connected (user: %s): ".formatted(getSocketId(event), user));
        systemMessageSender.send("User %s is now connected".formatted(user));
    }

}
