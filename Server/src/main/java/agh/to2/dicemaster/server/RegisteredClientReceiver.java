package agh.to2.dicemaster.server;

import agh.to2.dicemaster.common.Message;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.MoveDTO;
import com.google.gson.Gson;

public class RegisteredClientReceiver {
    public void onRequest(String json) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);

        Class contentClass = message.getContentClass();

        if (contentClass.equals(GameConfigDTO.class)) {

        } else if (contentClass.equals(GameDTO.class)) {

        } else if (contentClass.equals(MoveDTO.class)) {

        }
    }
}
