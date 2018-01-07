package agh.to2.dicemaster.client.services;

import agh.to2.dicemaster.client.api.GameEventHandler;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class QueueService {

    private final ConnectionFactory connectionFactory;
    private SimpleMessageListenerContainer container;

    public QueueService(String serverAddress) {
        connectionFactory = new CachingConnectionFactory(serverAddress);
    }

    public String configureClientQueue() {
        container = new SimpleMessageListenerContainer();
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        TopicExchange exchange = new TopicExchange("diceMasterExchange");
        Queue queue = new AnonymousQueue();

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(exchange);

        container.setQueueNames(queue.getName());
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(queue.getName()));

        return queue.getName();
    }

    public void startListeningToGameStateChange(GameEventHandler gameEventHandler) {
        container.setupMessageListener(gameEventHandler);
        container.start();
    }

    public void stopListeningToGameStateChange() {
        container.stop();
    }
}
