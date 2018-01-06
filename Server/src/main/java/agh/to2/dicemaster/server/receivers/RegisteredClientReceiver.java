package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.common.Message;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;
import agh.to2.dicemaster.server.managers.GamesManager;
import agh.to2.dicemaster.server.managers.UsersManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisteredClientReceiver {

    private final UsersManager usersManager;

    private final GamesManager gamesManager;

    @Autowired
    public RegisteredClientReceiver(UsersManager usersManager, GamesManager gamesManager) {
        this.usersManager = usersManager;
        this.gamesManager = gamesManager;
    }

    public void onRequest(String json, String consumerQueueName) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);

        Class contentClass = message.getContentClass();

        if (contentClass.equals(GameConfigDTO.class)) {

        } else if (contentClass.equals(GameDTO.class)) {

        } else if (contentClass.equals(MoveDTO.class)) {

        }
    }
}
