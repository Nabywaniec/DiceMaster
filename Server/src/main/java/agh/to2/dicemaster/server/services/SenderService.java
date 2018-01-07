package agh.to2.dicemaster.server.services;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.DTO.RegistrationConfirmationDTO;
import agh.to2.dicemaster.server.DTO.RegistrationRejectionDTO;
import agh.to2.dicemaster.server.api.Game;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SenderService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendRegistrationRejection(RegistrationRejectionDTO rejectionDTO, String queueName){
        send(rejectionDTO, queueName);
    }
    public void sendGameState(GameDTO gameDTO, String queueName){
        send(gameDTO, queueName);
    }

    public void sendGames(Collection<Game> games, String queueName){
        send(games, queueName);
    }

    public void sendRegistrationConfirmation(RegistrationConfirmationDTO registrationConfirmationDTO,
                                                      String queueName){
        send(registrationConfirmationDTO, queueName);
    }

    private void send(Object object, String queueName) {
        rabbitTemplate.convertAndSend("diceMasterExchange", queueName, object, this::setJsonContentType);
    }

    private Message setJsonContentType(Message message) {
        message.getMessageProperties().setContentType("application/json");
        return message;
    }
}
