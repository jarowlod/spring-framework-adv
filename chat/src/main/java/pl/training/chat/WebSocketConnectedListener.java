package pl.training.chat;

import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
@Log
public class WebSocketConnectedListener implements ApplicationListener<SessionConnectedEvent> {

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        log.info("Socket connected: " + WebSocketUtils.getSocketId(event));
        log.info("Socket attributes: " + WebSocketUtils.getAttributes(event));
    }

}
