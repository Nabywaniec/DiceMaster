package agh.to2.dicemaster.server.listeners;

import agh.to2.dicemaster.server.receivers.RegisteredClientReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisteredClientListener implements MessageListener {

    private final RegisteredClientReceiver registeredClientReceiver;

    @Autowired
    public RegisteredClientListener(RegisteredClientReceiver registeredClientReceiver) {
        this.registeredClientReceiver = registeredClientReceiver;
    }

    @Override
    public void onMessage(Message message) {
        registeredClientReceiver.onRequest(new String(message.getBody()),
                message.getMessageProperties().getConsumerQueue());
    }
}
