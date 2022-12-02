package pl.training.chat.integration.scheduling;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log
public class StatusLogger {

    //@Scheduled(fixedDelay = 1000)
    //@Scheduled(cron = "0 */1 * * * *")
    @Scheduled(cron = "@daily")
    public void updateStatus() {
        log.info("Status update: " + Instant.now());
    }

}
