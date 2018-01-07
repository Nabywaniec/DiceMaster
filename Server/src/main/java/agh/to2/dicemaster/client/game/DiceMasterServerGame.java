package agh.to2.dicemaster.client.game;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.client.services.QueueService;
import agh.to2.dicemaster.client.services.SenderService;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;

public class DiceMasterServerGame extends ServerGame {
    private final SenderService senderService;
    private final QueueService queueService;

    public DiceMasterServerGame(GameDTO gameDTO,
                                SenderService senderService,
                                QueueService queueService,
                                GameEventHandler gameEventHandler) {
        super(gameDTO);
        this.senderService = senderService;
        this.queueService = queueService;

        queueService.startListeningToGameStateChange(gameEventHandler);
    }

    @Override
    public void makeMove(MoveDTO moveDTO) {
        senderService.sendMove(moveDTO);
    }

    @Override
    public void leaveGame() {
        queueService.stopListeningToGameStateChange();
        senderService.sendLeaveGameRequest();
    }
}
