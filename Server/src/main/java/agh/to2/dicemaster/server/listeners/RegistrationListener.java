package agh.to2.dicemaster.server.listeners;

import agh.to2.dicemaster.server.receivers.RegistrationReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements MessageListener {

    private final RegistrationReceiver registrationReceiver;

    @Autowired
    public RegistrationListener(RegistrationReceiver registrationReceiver) {
        this.registrationReceiver = registrationReceiver;
    }

    @Override
    public void onMessage(Message message) {
        registrationReceiver.onRegistrationRequest(new String(message.getBody()),
                message.getMessageProperties()
                        .getHeaders().getOrDefault("clientQueueName", "undefined").toString());
    }
}
