module pl.training.payments.adapters {
    exports pl.training.payments.adapters.input.rest;
    exports pl.training.payments.adapters.output.time;
    exports pl.training.payments.adapters.output.persistence.jpa;

    requires pl.training.payments.ports;
    requires pl.training.commons;

    requires org.javamoney.moneta;
    requires spring.context;
    requires spring.boot;
    requires spring.core;
    requires spring.aop;
    requires spring.beans;
    requires spring.web;
    requires spring.webmvc;
    requires spring.tx;
    requires spring.data.jpa;
    requires spring.data.mongodb;
    requires spring.data.commons;
    requires spring.boot.starter.web;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.starter.validation;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.validator;
    requires jakarta.validation;
    requires org.aspectj.weaver;
    requires static lombok;
    requires static org.mapstruct;
    requires static org.mapstruct.processor;
}
