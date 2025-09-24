package br.com.fiap._tdspw_rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //Injeta os valores do application.properties
    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;
    @Value("${app.rabbitmq.queue}")
    private String queueName;
    @Value("${app.rabbitmq.routingkey}")
    private String rountingKey;

    @Bean
    Queue queue() {
        // Cria a fila. Por padrão é durável, não exclusiva, não auto-delete
        return new Queue(queueName, true);
    }

    @Bean
    DirectExchange exchange() {
        // Cria a direct exchange
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        // Cria o binding entre a queue e a exchange usando a routing key
        return BindingBuilder.bind(queue).to(exchange).with(rountingKey);
    }

}
