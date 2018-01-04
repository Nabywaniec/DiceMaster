package agh.to2.dicemaster.bot.model;

import agh.to2.dicemaster.bot.DiceInputDTO;
import agh.to2.dicemaster.bot.DiceOutputDTO;
import agh.to2.dicemaster.bot.IOConverter;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.server.User;

public abstract class Bot extends User{

    abstract DiceOutputDTO getDicesToThrow(DiceInputDTO input);

    @Override
    public void notifyGameStateChange(GameDTO gameDTO) {

        IOConverter converter = new IOConverter();

        DiceInputDTO diceInput = converter.getDiceInputDTO(gameDTO);

        DiceOutputDTO diceOutput = this.getDicesToThrow(diceInput);

        converter.setGameState(diceOutput);

    }

}
