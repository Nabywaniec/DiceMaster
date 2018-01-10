import java.util.List;
import java.util.Random;

public class NPlus implements Rules {
    public Integer aim;

    @Override
    public Integer getresult(Player player) {
        Integer result = 0;
        for(Dice d : player.getDices()){
            result+=d.getValue();
        }
        return result;
    }

    @Override
    public int initializeRound(List<Player> players) {
        int[] dices = new int[5];
        Integer aim = 0;
        Random generator = new Random();
        for (int i = 0; i < 5; i++) {
            dices[i] = generator.nextInt(6) + 1;
            aim += dices[i];
        }
        return generator.nextInt(players.size());

    }

    @Override
    public void initializeDices(Player player){
        for(int i = 0; i < 5; i++){
            player.setDice(i, Dice.randomDice());
        }
    }


    @Override
    public void drawDices(Player player, MoveDTO move){
        for(int i = 0; i < 5; i++){
            if(move.dicesToReroll[i]) player.setDice(i, Dice.randomDice());
        }
    }

    @Override
    public Integer getAim() {
        return aim;
    }
}
