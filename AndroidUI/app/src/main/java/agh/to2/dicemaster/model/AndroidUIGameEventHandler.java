package agh.to2.dicemaster.model;

import agh.to2.dicemaster.activity.TableActivity;
import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.common.api.GameDTO;

/**
 * Created by Artur on 20.01.2018.
 */

public class AndroidUIGameEventHandler implements GameEventHandler {
    //this class relays message on game change to the current table activity

    public static AndroidUIGameEventHandler gameEventHandler;
    private TableActivity currentTable;
    private GameDTO gameDTO;
    private boolean isTableActive = false;


    public AndroidUIGameEventHandler(){
        this.gameEventHandler = this;
    }


    @Override
    public void onGameChange(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
        if (isTableActive) {
            currentTable.onGameChange(gameDTO);
        }
    }

    public void setCurrentTable(TableActivity currentTable) {
        this.gameDTO = AppState.appstate.getServerGame().getGameDTO();
        this.currentTable = currentTable;
        this.isTableActive = true;
        this.currentTable.refresh(this.gameDTO);
    }

    public void exitCurrentTable() {
        this.isTableActive = false;
    }
}
