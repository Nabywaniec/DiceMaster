package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.server.managers.UsersManager;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RegistrationReceiver {

    private final UsersManager usersManager;

    private final SimpleMessageListenerContainer listenerContainer;

    @Autowired
    public RegistrationReceiver(UsersManager usersManager,
                                @Qualifier("clientListenerContainer") SimpleMessageListenerContainer listenerContainer) {
        this.usersManager = usersManager;
        this.listenerContainer = listenerContainer;
    }

    public void onRegistrationRequest(String username, String responseQueueName) {
        if (responseQueueName.equals("undefined")) {
            return;
        }

        System.out.println(username);
    }
}
