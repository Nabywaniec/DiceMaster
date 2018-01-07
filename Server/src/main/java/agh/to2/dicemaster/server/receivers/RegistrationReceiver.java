package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.server.DTO.RegistrationRejectionDTO;
import agh.to2.dicemaster.server.DTO.RegistrationRequestDTO;
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


    public void onRegistrationRequest(RegistrationRequestDTO requestDTO) {
        if(requestDTO.getUsername().startsWith("bot#")){
            senderService.sendRegistrationRejection(
                    new RegistrationRejectionDTO(), requestDTO.getClientQueueName());
        } else if(usersManager.getUserById(requestDTO.getUsername()).isPresent()){
            senderService.sendRegistrationRejection(
                    new RegistrationRejectionDTO(), requestDTO.getClientQueueName());
        } else {
            User createdUser = usersManager.createUser(requestDTO.getUsername(),
                    requestDTO.getClientQueueName());
            createdUser.sendCreationConfirmation();
            queueService.addRegisteredClientQueue(createdUser.getServerQueueName());
        }
    }
}
