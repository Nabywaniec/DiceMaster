package agh.to2.dicemaster.server.services;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.DTO.RegistrationConfirmationDTO;
import agh.to2.dicemaster.server.api.Game;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SenderService {

    public void sendRegistrationRejection(String queueName){

    }
    public void sendGameState(GameDTO gameDTO, String queueName){

    }

    public void sendGames(Collection<Game> games, String queueName){

    }

    public void sendRegistrationConfirmation(RegistrationConfirmationDTO registrationConfirmationDTO,
                                                      String queueName){

    }
}
