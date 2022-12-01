package pl.training.chat;

import lombok.Value;
import lombok.With;

import java.util.Set;

@Value
public class ChatMessageDto {

    String sender;
    Set<String> recipients;
    String text;
    @With
    String timestamp;

}
