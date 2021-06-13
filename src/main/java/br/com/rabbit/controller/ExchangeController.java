package br.com.rabbit.controller;

import br.com.rabbit.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "Producer")
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/json/{exchange}/{routingKey}")
    @Operation(summary = "Endpoint que recebe os dados para enviar uma mensagem ao RabbitMq")
    ResponseEntity<Object> jsonOnExchange(@PathVariable("exchange") final String exchange,
                        @PathVariable("routingKey") final String routingKey,
                        @RequestBody Book book) {
        log.info("Sending message: {}", book);
        rabbitTemplate.convertAndSend(exchange, routingKey, book);
        return ResponseEntity.ok().build();
    }

}
