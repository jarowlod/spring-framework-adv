open module pl.training.orders.adapters {
    exports pl.training.orders.adapters.input.rest;

    requires pl.training.orders.ports;

    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.data.mongodb;
    requires spring.web;
    requires spring.webmvc;
    requires spring.boot.starter.web;
    requires static lombok;
    requires static org.mapstruct;
    requires static org.mapstruct.processor;
    requires spring.data.commons;
    requires java.logging;
}
