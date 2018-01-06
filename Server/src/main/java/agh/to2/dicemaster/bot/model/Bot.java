package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.IOConverter;
import agh.to2.dicemaster.bot.MoveDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.User;

public abstract class Bot extends User{

    abstract DiceOutputDTO getDicesToThrow(DiceInputDTO input);

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

        IOConverter converter = new IOConverter();

        DiceInputDTO diceInput = converter.getDiceInputDTO(gameDTO);

        DiceOutputDTO diceOutput = this.getDicesToThrow(diceInput);

        MoveDTO moveDTO = converter.setGameState(diceOutput);

        //this field is assigned to every User
        // FIXME the line below is absolutely necessary for proper workflow, but does not compile
        //this.playerEventHandler.onMoveRequest(moveDTO);

    }

}
