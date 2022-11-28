open module pl.training.shop {
    requires pl.training.payments.domain;
    requires pl.training.payments.adapters;
    requires transitive pl.training.payments.ports;

    requires spring.beans;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.aop;
    requires org.aspectj.weaver;
    requires java.sql;
    requires spring.data.jpa;
    requires spring.webmvc;
    requires spring.tx;
    requires spring.core;
}
