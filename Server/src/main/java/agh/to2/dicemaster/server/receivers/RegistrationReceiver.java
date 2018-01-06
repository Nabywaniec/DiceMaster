package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.server.User;
import agh.to2.dicemaster.server.managers.UsersManager;
import agh.to2.dicemaster.server.services.SenderService;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationReceiver {
    private final UsersManager usersManager;
    private final SenderService senderService;

    @Autowired
    public RegistrationReceiver(UsersManager usersManager, SenderService senderService) {
        this.usersManager = usersManager;
        this.senderService = senderService;
    }


    public void onRegistrationRequest(String username, String senderQueueName) {
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
