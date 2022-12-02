package pl.training.chat;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Set;

@Builder
@Value
public class ChatMessageDto {

    String sender;
    Set<String> recipients;
    String text;
    @With
    String timestamp;

    public boolean isForALl() {
        if (recipients == null) {
            return true;
        }
        return recipients.stream().anyMatch(String::isEmpty);
    }

}
