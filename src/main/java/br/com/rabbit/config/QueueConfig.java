package br.com.rabbit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    Queue jsonQueue() {
        return QueueBuilder
                .durable("JSON-QUEUE")
                .build();
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper mapper){
        return new Jackson2JsonMessageConverter(mapper);
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter(mapper));
        return rabbitTemplate;
    }

}
