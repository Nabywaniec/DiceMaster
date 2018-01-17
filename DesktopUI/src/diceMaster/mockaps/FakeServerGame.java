package diceMaster.mockaps;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.UserInGame;
import diceMaster.model.server.ServerGame;

import java.util.LinkedList;
import java.util.List;

public class FakeServerGame extends ServerGame {
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
