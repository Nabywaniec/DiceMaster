package agh.to2.dicemaster.server.services;

import agh.to2.dicemaster.server.listeners.RegisteredClientListener;
import agh.to2.dicemaster.server.listeners.RegistrationListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    private final SimpleMessageListenerContainer clientListenerContainer;

    @Autowired
    public QueueService(SimpleMessageListenerContainer clientListenerContainer) {
        this.clientListenerContainer = clientListenerContainer;
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

    @Bean
    SimpleMessageListenerContainer registrationContainer(ConnectionFactory connectionFactory,
                                                         RegistrationListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("registrationQueue");
        container.setMessageListener(listener);
        return container;
    }

    @Bean(name = "clientListenerContainer")
    SimpleMessageListenerContainer registeredClientContainer(ConnectionFactory connectionFactory,
                                                             RegisteredClientListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(listener);
        return container;
    }

    public void addClientQueueName(String queueName) {
        clientListenerContainer.addQueueNames(queueName);
    }
}
