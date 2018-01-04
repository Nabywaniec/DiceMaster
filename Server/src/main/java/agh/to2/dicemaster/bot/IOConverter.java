package agh.to2.dicemaster.bot;

import agh.to2.dicemaster.common.api.GameDTO;

public class IOConverter {

    public DiceInputDTO getDiceInputDTO(GameDTO input){
        DiceInputDTO output = new DiceInputDTO();

        //TODO implement this method

        return output;
    }

    public MoveDTO setGameState(DiceOutputDTO output) {

        // output is converted into MoveDTO....
        return new MoveDTO();

        // TODO implement this method
    }
}
