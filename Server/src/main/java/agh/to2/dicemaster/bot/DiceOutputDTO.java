package agh.to2.dicemaster.bot;

import java.util.List;

public class DiceOutputDTO {
    private List<Integer> dicesToThrow;


    public List<Integer> getDicesToThrow() {
        return dicesToThrow;
    }

    public void setDicesToThrow(List<Integer> dicesToThrow) {
        this.dicesToThrow = dicesToThrow;
    }
}
