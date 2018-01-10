package agh.to2.dicemaster.server.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueBindings {
    @Bean(name = "registrationBinding")
    Binding registration(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);

        String registrationQueueName = "registrationQueue";

        Queue queue = new Queue("registrationQueue");
        admin.declareQueue(queue);

        TopicExchange exchange = new TopicExchange("diceMasterExchange");
        admin.declareExchange(exchange);

        return BindingBuilder.bind(queue).to(exchange).with(registrationQueueName);
    }

    @Bean(name = "queueDeletionEvents")
    Binding queueDeletion(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);

        Queue queue = new Queue("queueDeletionEvents");
        admin.declareQueue(queue);

        TopicExchange exchange = new TopicExchange("amq.rabbitmq.event");

        return BindingBuilder.bind(queue).to(exchange).with("queue.deleted");
    }
}
