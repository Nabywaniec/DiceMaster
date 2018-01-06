package agh.to2.dicemaster.server.services;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.DTO.RegistrationConfirmationDTO;
import agh.to2.dicemaster.server.api.Game;

import java.util.Collection;

public interface SenderService {

    void sendRegistrationRejection(String queueName);
    void sendGameState(GameDTO gameDTO, String queueName);

    void sendGames(Collection<Game> games, String queueName);

    void sendRegistrationConfirmation(RegistrationConfirmationDTO registrationConfirmationDTO,
                                                      String queueName);
}
