package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.managers.UsersManager;
import agh.to2.dicemaster.server.services.QueueService;
import agh.to2.dicemaster.server.services.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationReceiver {
    private final UsersManager usersManager;
    private final SenderService senderService;

    private final QueueService queueService;

    @Autowired
    public RegistrationReceiver(UsersManager usersManager, QueueService queueService, SenderService senderService) {
        this.usersManager = usersManager;
        this.queueService = queueService;
        this.senderService = senderService;
    }


    public void onRegistrationRequest(String username, String clientQueueName) {
        if (clientQueueName.equals("undefined")) {
            return;
        }
        if(username.startsWith("bot#")){
            senderService.sendRegistrationRejection(clientQueueName);
        } else if(usersManager.getUserById(username).isPresent()){
            senderService.sendRegistrationRejection(clientQueueName);
        } else {
            User createdUser = usersManager.createUser(username, clientQueueName);
            createdUser.sendCreationConfirmation();
            queueService.addRegisteredClientQueueName(createdUser.getServerQueueName());
        }
    }
}
