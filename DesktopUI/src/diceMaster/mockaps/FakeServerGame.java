package diceMaster.mockaps;

import agh.to2.dicemaster.common.api.*;
import diceMaster.model.gui.GameEventHandler;
import diceMaster.model.server.ServerGame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FakeServerGame extends ServerGame {
    private String userName;
    private GameEventHandler gameEventHandler;
    private GameDTO gameDTO;

    public FakeServerGame(String userName, GameEventHandler gameEventHandler){
        this.userName = userName;
        this.gameEventHandler = gameEventHandler;

        Dices dices = new Dices();

        List<UserInGame> players = new LinkedList<>();
        for(int i=0; i<14; i++){
            String nick = "Player" + i;

            Random rand = new Random();
            for(int j=0; j<5; j++)
                dices.getDicesScore()[j] = fromIntToDicesNumber(rand.nextInt(6) + 1);

            UserInGame u = new UserInGame(nick,dices, i + 2, false);

            if(i == 8) u.setHisTurn(true);
            if(i == 9) players.add(new UserInGame(this.userName,dices, 0, false));

            players.add(u);
        }
        GameConfigDTO gc = new GameConfigDTO("takasytulacja", 14, GameType.NPLUS, 0, 0, 25);
        this.gameDTO = new GameDTO(3, gc, players, null);
    }

    public void makeMove(MoveDTO moveDTO){
        Dices thisUserDices = new Dices();
        for(int i=0; i < this.gameDTO.getPlayers().size(); i++) {
            if(this.gameDTO.getPlayers().get(i).getUserName() == this.userName)
                thisUserDices = this.gameDTO.getPlayers().get(i).getDices();
        }

        Random rand = new Random();
        for(int i=0; i<5; i++)
            if(moveDTO.getDicesToReRoll()[i]) {
                thisUserDices.getDicesScore()[i] = fromIntToDicesNumber(rand.nextInt(6) + 1);
            }
        this.gameEventHandler.refreshGame(this.gameDTO);
    }

    public GameDTO getGameDTO(){
        return this.gameDTO;
    }

    private DiceNumbers fromIntToDicesNumber(int i){
        if(i == 1)
            return DiceNumbers.ONE;
        if(i == 2)
            return DiceNumbers.TWO;
        if(i == 3)
            return DiceNumbers.THREE;
        if(i == 4)
            return DiceNumbers.FOUR;
        if(i == 5)
            return DiceNumbers.FIVE;
        return DiceNumbers.SIX;
    }
}
