package pl.training.chat;

import lombok.*;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private String sender;
    private Set<String> recipients;
    private String text;
    @With
    private String timestamp;

    public boolean isForALl() {
        if (recipients == null) {
            return true;
        }
        return recipients.stream().anyMatch(String::isEmpty);
    }

}
