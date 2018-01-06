package agh.to2.dicemaster.server.services;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    private final SimpleMessageListenerContainer clientListenerContainer;

    @Autowired
    public QueueService(@Qualifier("clientListenerContainer") SimpleMessageListenerContainer clientListenerContainer) {
        this.clientListenerContainer = clientListenerContainer;
    }

    public void addRegisteredClientQueueName(String queueName) {
        clientListenerContainer.addQueueNames(queueName);
    }
}
