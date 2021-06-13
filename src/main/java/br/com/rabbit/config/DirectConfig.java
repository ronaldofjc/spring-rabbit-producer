package br.com.rabbit.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DirectConfig {

    private final Queue jsonQueue;

    @Bean
    Exchange directExchange() {
        return ExchangeBuilder
                .directExchange("DIRECT-EXCHANGE")
                .durable(true)
                .build();
    }

    @Bean
    Binding jsonDirectBinding() {
        return BindingBuilder
                .bind(jsonQueue)
                .to(directExchange())
                .with("TO-JSON-QUEUE")
                .noargs();
    }


}
