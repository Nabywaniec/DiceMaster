package agh.to2.dicemaster.server;

import agh.to2.dicemaster.server.receivers.RegistrationReceiver;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Adam Gapiński
 */

@SpringBootApplication
public class Application {
    private static ConnectionFactory cf = new CachingConnectionFactory();

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        configureRegistrationQueue();
        setupRegistrationReceiver();
    }

    private static void configureRegistrationQueue() {
        // set up the queue, exchange, binding on the broker
        RabbitAdmin admin = new RabbitAdmin(cf);

        String registrationQueueName = "registrationQueue";
        Queue queue = new Queue(registrationQueueName);
        admin.declareQueue(queue);

        TopicExchange exchange = new TopicExchange("exchange");
        admin.declareExchange(exchange);
        admin.declareBinding(
                BindingBuilder.bind(queue).to(exchange).with(registrationQueueName));
    }

    private static void setupRegistrationReceiver() {
        // set up the listener and container
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);

        MessageListenerAdapter adapter = new MessageListenerAdapter(new RegistrationReceiver(),
                "onRegistrationRequest");

        container.setMessageListener(adapter);
        container.setQueueNames("registrationQueue");
        container.start();
    }
}
