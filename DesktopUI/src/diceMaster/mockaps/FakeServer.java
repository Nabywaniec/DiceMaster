package diceMaster.mockaps;



import agh.to2.dicemaster.common.api.*;

import diceMaster.model.gui.GameEventHandler;
import diceMaster.model.server.Server;
import diceMaster.model.server.ServerGame;

import java.util.LinkedList;
import java.util.List;

public class FakeServer implements Server {
    @Override
    public ServerGame createGame(GameConfigDTO gameConfigDTO, GameEventHandler gameEventHandler, UserType userType) {
        return new ServerGame();
    }

    @Override
    public ServerGame requestJoinGame(GameDTO gameDTO, GameEventHandler gameEventHandler, UserType userType) {
        return null;
    }

    @Override
    public List<GameDTO> getAvailableGames() {
        DiceNumbers[] aaa = new DiceNumbers[5];
        aaa[0] = DiceNumbers.UNKNOWN;
        aaa[1] = DiceNumbers.UNKNOWN;
        aaa[2] = DiceNumbers.UNKNOWN;
        aaa[3] = DiceNumbers.UNKNOWN;
        aaa[4] = DiceNumbers.UNKNOWN;
        GameConfigDTO gc = new GameConfigDTO("aaa", 5, GameType.NPLUS, 2, 2, 2);
        LinkedList<UserInGame> p = new LinkedList<UserInGame>();
        p.add(new UserInGame("User 1", new Dices(aaa), 0, false));
        LinkedList<String> obs = new LinkedList<>();
        obs.add("bot1");
        LinkedList<GameDTO> toR = new LinkedList<GameDTO>();
        toR.add(new GameDTO(1, gc, p,obs));
        return toR;
    }

    @Override
    public boolean registerClient(String username) {
        return true;
    }

    public GameDTO getGameDTO(){
        List<UserInGame> players = new LinkedList<>();

        for(int i=0; i<7; i++){
            String nick = "Player" + i;
            UserInGame u = new UserInGame(nick,null, i + 15, false);

            if(i == 5) u.setHisTurn(true);

            players.add(u);
        }

        return new GameDTO(3, null, players, null);
    }
}
