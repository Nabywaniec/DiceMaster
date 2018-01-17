package diceMaster.model.server;

import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;

public class ServerGame {
    public ServerGame(){
    }
    private GameDTO gameDTO;
    public void makeMove(MoveDTO moveDTO){};
    public void leaveGame(){};
}
