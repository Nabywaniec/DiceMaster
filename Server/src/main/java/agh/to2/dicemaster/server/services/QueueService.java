package agh.to2.dicemaster.server.services;

import agh.to2.dicemaster.server.receivers.RegisteredClientReceiver;
import agh.to2.dicemaster.server.receivers.RegistrationReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueService {
    @Bean(name = "clientListenerContainer")
    SimpleMessageListenerContainer registeredClientContainer(ConnectionFactory connectionFactory,
                                                             RegisteredClientReceiver receiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener((MessageListener) message -> receiver.onRequest(new String(message.getBody()),
                message.getMessageProperties().getConsumerQueue()));
        return container;
    }

    @Bean
    SimpleMessageListenerContainer registrationContainer(ConnectionFactory connectionFactory,
                                                         RegistrationReceiver receiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("registrationQueue");
        container.setMessageListener((MessageListener) message ->
                receiver.onRegistrationRequest(new String(message.getBody()),
                        message.getMessageProperties()
                                .getHeaders().getOrDefault("clientQueueName", "undefined").toString()));
        return container;
    }

    @Bean
    Binding binding(ConnectionFactory connectionFactory) {
        // set up the queue, exchange, binding on the broker
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);

        String registrationQueueName = "registrationQueue";

        Queue queue = new Queue("registrationQueue");
        admin.declareQueue(queue);

        TopicExchange exchange = new TopicExchange("exchange");
        admin.declareExchange(exchange);
        return BindingBuilder.bind(queue).to(exchange).with(registrationQueueName);
    }
}
