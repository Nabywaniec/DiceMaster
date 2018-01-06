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


    public void onRegistrationRequest(String username, String senderQueueName) {
        if (senderQueueName.equals("undefined")) {
            return;
        }
        if(username.startsWith("bot#")){
            senderService.sendRegistrationRejection(senderQueueName);
        } else if(usersManager.getUserById(username).isPresent()){
            senderService.sendRegistrationRejection(senderQueueName);
        } else {
            User createdUser = usersManager.createUser(username, senderQueueName);
            createdUser.sendCreationConfirmation();
            queueService.addRegisteredClientQueueName(createdUser.getServerQueueName());
        }
    }
}
