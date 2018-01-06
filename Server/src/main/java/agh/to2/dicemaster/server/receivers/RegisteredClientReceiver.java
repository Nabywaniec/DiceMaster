package agh.to2.dicemaster.server.receivers;

import agh.to2.dicemaster.server.managers.GamesManager;
import agh.to2.dicemaster.server.managers.UsersManager;
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
    }
}
