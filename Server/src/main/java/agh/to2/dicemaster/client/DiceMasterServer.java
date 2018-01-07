package agh.to2.dicemaster.client;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.Server;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.client.game.DiceMasterServerGame;
import agh.to2.dicemaster.client.services.QueueService;
import agh.to2.dicemaster.client.services.SenderService;
import agh.to2.dicemaster.common.UserType;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.DTO.RegistrationConfirmationDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiceMasterServer implements Server {

    private boolean registered = false;
    private final QueueService queueService;
    private final SenderService senderService;

    public DiceMasterServer(String serverAddress) {
        senderService = new SenderService(serverAddress, 5000);
        queueService = new QueueService(serverAddress);

        queueService.configureClientQueue();
    }

    @Override
    public ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType) {
        validateRegistered();

        Optional<Object> response = senderService.requestGameCreation(gameConfigDTO, userType);

        if (response.isPresent()) {
            Object responseObject = response.get();
            if (responseObject instanceof GameDTO) {
                return new DiceMasterServerGame((GameDTO) responseObject, senderService, queueService, gameEventHandler);
            }
        }

        return null;
    }

    @Override
    public ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType) {
        validateRegistered();

        Optional<Object> response = senderService.requestGameJoin(gameDTO, userType);

        if (response.isPresent()) {
            Object responseObject = response.get();
            if (responseObject instanceof GameDTO) {
                return new DiceMasterServerGame((GameDTO) responseObject, senderService, queueService, gameEventHandler);
            }
        }

        return null;
    }

    @Override
    public List<GameDTO> getAvailableGames() {
        validateRegistered();

        List<GameDTO> result = new ArrayList<>();
        senderService.requestGames().ifPresent(o -> convertToGameDTOList(o, result));

        return result;
    }

    private void convertToGameDTOList(Object responseObject, List<GameDTO> result) {
        if (responseObject instanceof List) {
            List gameDTOS = (List) responseObject;

            for (Object o : gameDTOS) {
                if (o instanceof GameDTO) {
                    result.add((GameDTO) o);
                }
            }
        }
    }

    @Override
    public boolean registerClient(String username) {
        String clientQueueName = queueService.configureClientQueue();

        Optional<Object> response = senderService.requestRegistration(username, clientQueueName);

        if (response.isPresent()) {
            Object responseObject = response.get();

            if (responseObject instanceof RegistrationConfirmationDTO) {
                RegistrationConfirmationDTO confirmationDTO = (RegistrationConfirmationDTO) responseObject;
                senderService.setServerQueueName(confirmationDTO.getServerQueueName());
                registered = true;
                return true;
            }
        }

        return false;
    }



    private void validateRegistered() {
        if (!registered) {
            throw new IllegalStateException("User is not registered");
        }

    }
}
