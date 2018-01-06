package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.managers.UsersManager;
import agh.to2.dicemaster.server.services.SenderService;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RegistrationReceiver {
    private final UsersManager usersManager;
    private final SenderService senderService;

    private final SimpleMessageListenerContainer listenerContainer;



    @Autowired
    public RegistrationReceiver(UsersManager usersManager,
                                @Qualifier("clientListenerContainer") SimpleMessageListenerContainer listenerContainer,
                                SenderService senderService) {
        this.usersManager = usersManager;
        this.listenerContainer = listenerContainer;
        this.senderService = senderService;
    }


    public void onRegistrationRequest(String username, String senderQueueName) {
        if (senderQueueName.equals("undefined")) {
            return;
        }
//        TODO: Remove sout:
        System.out.println(username);
        if(username.startsWith("bot#")){
            senderService.sendRegistrationRejection(senderQueueName);
        } else if(usersManager.getUserById(username).isPresent()){
            senderService.sendRegistrationRejection(senderQueueName);
        } else {
            User createdUser = usersManager.createUser(username, senderQueueName);
            createdUser.sendCreationConfirmation();
        }
    }
}
