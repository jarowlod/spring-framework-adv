package pl.training.chat.integration.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pl.training.chat.ChatEventEmitter;
import pl.training.chat.ChatMessageDto;

import java.util.Map;

@Primary
@Service
@RequiredArgsConstructor
public class ChatMailSender implements ChatEventEmitter {

    private static final String SENDER = "chat@training.pl";
    private static final String SUBJECT = "Notification";
    private static final String TEMPLATE_NAME = "ChatNotification";
    private static final String LANGUAGE = "pl";

    private final TemplateService templateService;
    private final JavaMailSender mailSender;

    @Override
    public void emit(ChatMessageDto chatMessageDto) {
        Map<String, Object> parameters = Map.of("value", chatMessageDto.toString());
        var text = templateService.evaluate(TEMPLATE_NAME, parameters, LANGUAGE);
        var mailMessage = createMessage(SENDER,new String[] { "recipient@training.pl" }, SUBJECT, text);
        mailSender.send(mailMessage);
    }

    private MimeMessagePreparator createMessage(String sender, String[] recipients, String subject, String text) {
        return mimeMessage -> {
            var mailMessage = new MimeMessageHelper(mimeMessage);
            mailMessage.setFrom(sender);
            mailMessage.setTo(recipients);
            mailMessage.setText(text, true);
        };
    }

}
