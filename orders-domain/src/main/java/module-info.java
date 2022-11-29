module pl.training.orders.domain {
    exports pl.training.orders.domain.services;

    requires java.logging;
    requires pl.training.orders.ports;
    requires static lombok;
}
