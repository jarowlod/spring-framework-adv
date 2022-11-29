module pl.training.payments.domain {
    exports pl.training.payments.domain.services;

    requires pl.training.payments.ports;
    requires pl.training.commons;

    requires org.javamoney.moneta;
    requires static lombok;
    requires static org.mapstruct;
    requires static org.mapstruct.processor;
}
