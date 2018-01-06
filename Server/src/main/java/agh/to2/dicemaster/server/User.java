package agh.to2.dicemaster.server;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.DTO.RegistrationConfirmationDTO;
import agh.to2.dicemaster.server.api.Game;
import agh.to2.dicemaster.server.api.GameParticipant;
import agh.to2.dicemaster.server.api.PlayerEventHandler;
import agh.to2.dicemaster.server.services.SenderService;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Collection;

public class User extends GameParticipant {
    private String serverQueueName;
    private String clientQueueName;
    private PlayerEventHandler playerEventHandler;
    private SenderService senderService;

    public User(String username, String clientQueueName) {
        this.clientQueueName = clientQueueName;
        this.setId(username);

        this.serverQueueName = generateQueueName();
    }

    private String generateQueueName() {
//        return UUID.randomUUID().toString()
        return DatatypeConverter.printHexBinary((new SecureRandom().generateSeed(32)));
    }

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {
        this.senderService.sendGameState(gameDTO, this.clientQueueName);
    }

    @Override
    public void registerPlayerEventHandler(PlayerEventHandler playerEventHandler) {
        this.playerEventHandler = playerEventHandler;
    }

    public void sendGames(Collection<Game> games) {
        this.senderService.sendGames(games, this.clientQueueName);
    }

    public void sendCreationConfirmation(){
        senderService.sendRegistrationConfirmation(new RegistrationConfirmationDTO(this.serverQueueName), clientQueueName);
    }

    public String getServerQueueName() {
        return serverQueueName;
    }

    public void setServerQueueName(String serverQueueName) {
        this.serverQueueName = serverQueueName;
    }

    public String getClientQueueName() {
        return clientQueueName;
    }

    public void setClientQueueName(String clientQueueName) {
        this.clientQueueName = clientQueueName;
    }

    public PlayerEventHandler getPlayerEventHandler() {
        return playerEventHandler;
    }
}
