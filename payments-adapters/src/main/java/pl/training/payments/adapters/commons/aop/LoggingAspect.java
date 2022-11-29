package pl.training.payments.adapters.commons.aop;

import lombok.extern.java.Log;
import pl.training.payments.ports.model.PaymentPort;

@Log
public class LoggingAspect {

    public void beforeLog() {
        log.info("Before...");
    }

    public void afterReturningLog(PaymentPort result) {
        log.info("After returning..." + result);
    }

}
