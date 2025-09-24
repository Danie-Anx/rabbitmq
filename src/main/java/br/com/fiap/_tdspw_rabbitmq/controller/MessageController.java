package br.com.fiap._tdspw_rabbitmq.controller;

import br.com.fiap._tdspw_rabbitmq.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageProducer producer;
    @Autowired
    public MessageController (MessageProducer messageProducer) {
        this.producer = messageProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            producer.sendMessage(message);
            return ResponseEntity.ok("Mensagem enviada para a fila: " + message);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Erro ao enviar mensagem: " + e.getMessage());
        }
    }
}
