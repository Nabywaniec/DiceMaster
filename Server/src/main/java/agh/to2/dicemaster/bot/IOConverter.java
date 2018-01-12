package agh.to2.dicemaster.bot;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;

public class IOConverter {

    public DiceInputDTO getDiceInputDTO(GameDTO input){
        DiceInputDTO output = new DiceInputDTO();

        //TODO implement this method

        return output;
    }

    public MoveDTO makeNewMoveDTO(DiceOutputDTO output) {

        // output is converted into MoveDTO....
//        return new MoveDTO();
        return null;
        // TODO implement this method
    }
}
