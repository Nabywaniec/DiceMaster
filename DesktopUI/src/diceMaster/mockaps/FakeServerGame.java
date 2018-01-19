package diceMaster.mockaps;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FakeServerGame extends ServerGame {
    private String userName;
    private GameEventHandler gameEventHandler;
    private GameDTO gameDTO;

    public FakeServerGame(GameDTO gameDTO, String userName, GameEventHandler gameEventHandler) {
        super(gameDTO);
        this.userName = userName;
        this.gameEventHandler = gameEventHandler;


        List<UserInGame> players = new LinkedList<>();
        for (int i = 0; i < 14; i++) {
            String nick = "Player" + i;

            Dices dices = new Dices();
            Random rand = new Random();
            for (int j = 0; j < 5; j++)
                dices.getDicesScore()[j] = fromIntToDicesNumber(rand.nextInt(6) + 1);

            UserInGame u = new UserInGame(nick, dices, i + 2, false);

            if (i == 9)
                players.add(new UserInGame(this.userName, dices, 0, true));
            else
                players.add(u);
        }
        GameConfigDTO gc = new GameConfigDTO("takasytulacja", 14, GameType.NPLUS, 0, 0, 25);
        this.gameDTO = new GameDTO(3, gc, players, null);
        this.gameDTO.setScoreToWin(18);
    }

    public void makeMove(MoveDTO moveDTO) {
        Dices thisUserDices = new Dices();
        int id = 0;
        for (int i = 0; i < this.gameDTO.getPlayers().size(); i++) {
            if (this.gameDTO.getPlayers().get(i).getUserName() == this.userName) {
                thisUserDices = this.gameDTO.getPlayers().get(i).getDices();
                id = i;
            }
        }

        Random rand = new Random();
        for (int i = 0; i < 5; i++)
            if (moveDTO.getDicesToReRoll()[i]) {
                thisUserDices.getDicesScore()[i] = fromIntToDicesNumber(rand.nextInt(6) + 1);
            }
        this.gameDTO.getPlayers().get(id).setDices(thisUserDices);
        System.out.println(this.gameDTO.getPlayers().get(id).getUserName());
        this.gameDTO.getPlayers().get(id).setHisTurn(false);
        this.gameDTO.getPlayers().get((id + 1) % this.gameDTO.getPlayers().size()).setHisTurn(true);
        this.gameEventHandler.onGameChange(this.gameDTO);
    }

    @Override
    public void leaveGame() {

    }

    public GameDTO getGameDTO() {
        return this.gameDTO;
    }

    private DiceNumbers fromIntToDicesNumber(int i) {
        if (i == 1)
            return DiceNumbers.ONE;
        if (i == 2)
            return DiceNumbers.TWO;
        if (i == 3)
            return DiceNumbers.THREE;
        if (i == 4)
            return DiceNumbers.FOUR;
        if (i == 5)
            return DiceNumbers.FIVE;
        return DiceNumbers.SIX;
    }
}
