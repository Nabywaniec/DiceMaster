package agh.to2.dicemaster.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import agh.to2.dicemaster.client.api.GameEventHandler;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.*;
import agh.to2.dicemaster.model.AndroidUIGameEventHandler;
import agh.to2.dicemaster.model.AppState;

public class TableActivity extends AppCompatActivity {
    private UserType userType;  //display correct view based on userType
    private GameDTO gameDTO;
    private String userName;
    private AppState appState;
    private ServerGame serverGame;
    private AndroidUIGameEventHandler gameEventHandler;

    //values 'extracted' from gameDTO and GameDTO.GameConfigDTO


    private TextView currentUserTypeTextView;

    private TextView tableNameTextView;
    private TextView tableTypeTextView;
    private TextView maxPlayersTextView;

    private TextView currentTurnTextView;
    private TextView pointsRequiredToWinTextView;
    private TextView currentPlayersTextView;



    private HorizontalScrollView playerInfoView;
    private TextView currentPointsTextView;
    private TextView isPlayerTurn;
    private ProgressBar timer;


    private ConstraintLayout dicesLayout;
    private Image diceImg1,diceImg2,diceImg3,diceImg4,diceImg5;
    private Switch dice1,dice2,dice3,dice4,dice5;


    private String userTypeMessage = "You are currently ";
    private String tableNameMessage = "table:";
    private String tableTypeMessage = "type:";
    private String maxPlayersMessage = "max players:";
    private String roundsToWin = "points required to win:";
    private String currentTurnMessage = "turn:";
    private String currentPlayerCountMessage = "current players:";
    private String isPlayerTurnNowMessage = "";
    private String playerPointsMessage = "your points:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);

        appState = AppState.appstate;
        userType = appState.getUserType();
        serverGame = appState.getServerGame();


        playerInfoView = (HorizontalScrollView) findViewById(R.id.scrollView_player_info);
        dicesLayout = (ConstraintLayout) findViewById(R.id.layout_dices);

        //these values might be changing
        currentUserTypeTextView = (TextView) findViewById(R.id.textView_playing_or_observing);

        tableNameTextView = (TextView) findViewById(R.id.textView_table_name);
        tableTypeTextView = (TextView) findViewById(R.id.textView_table_type);
        maxPlayersTextView = (TextView) findViewById(R.id.textView_max_players);
        //todo current round?
        currentTurnTextView = (TextView) findViewById(R.id.textView_current_turn);
        pointsRequiredToWinTextView = (TextView) findViewById(R.id.textView_max_rounds);
        currentPlayersTextView = (TextView) findViewById(R.id.textView_current_players);
        currentPointsTextView = (TextView) findViewById(R.id.textView_current_points);
        isPlayerTurn = (TextView) findViewById(R.id.textView_is_player_turn);
        timer = (ProgressBar) findViewById(R.id.timer);


        //hide some things if needed
        if(userType == UserType.OBSERVER){
            playerInfoView.setVisibility(View.GONE);
            dicesLayout.setVisibility(View.GONE);
        }

        Button exitButton = (Button) findViewById(R.id.button_exit_table);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //just exit table
                exitTable();
            }
        });

        Button rerollButton = (Button) findViewById(R.id.button_reroll_selected);
        rerollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });

        this.gameEventHandler = AndroidUIGameEventHandler.gameEventHandler;
        gameEventHandler.setCurrentTable(this);

    }



    private void exitTable() {
        //inform the game server that you are leaving
        appState.getServerGame().leaveGame();
        gameEventHandler.exitCurrentTable();
        switchToLobby();
    }

    private void onGameEnd(){
        //todo display some message, maybe a popup

        switchToLobby();
    }


    private void refreshValues(){
        userTypeMessage = "You are currently ";
        tableNameMessage = "table:";
        tableTypeMessage = "type:";
        maxPlayersMessage = "max players:";
        roundsToWin = "points required to win:";
        currentTurnMessage = "turn:";
        currentPlayerCountMessage = "current players:";
        isPlayerTurnNowMessage = "";
        playerPointsMessage = "your points:";
        //todo dices
        //todo switches?
        //todo players list

        userTypeMessage += userTypeMessage(appState.getUserType());
        tableNameMessage += gameDTO.getGameConfig().getTableName();
        maxPlayersMessage += gameDTO.getGameConfig().getMaxPlayers();
        roundsToWin += gameDTO.getGameConfig().getRoundsToWin();
        currentTurnMessage += "todo"; //todo
        currentPlayerCountMessage += gameDTO.getPlayers().size();
        isPlayerTurnNowMessage += "todo"; //todo
        playerPointsMessage += "todo"; //todo

        //refresh
        currentUserTypeTextView.setText(userTypeMessage);
        tableNameTextView.setText(tableNameMessage);
        tableTypeTextView.setText(tableTypeMessage);
        maxPlayersTextView.setText(maxPlayersMessage);
        pointsRequiredToWinTextView.setText(roundsToWin);
        currentTurnTextView.setText(currentTurnMessage);
        currentPlayersTextView.setText(currentPlayerCountMessage);
        isPlayerTurn.setText(isPlayerTurnNowMessage);
        currentPointsTextView.setText(playerPointsMessage);

    }

    private String userTypeMessage(UserType userType){
        String s ="";
        if(userType == UserType.OBSERVER){
            s = "observing";
        }else if(userType == UserType.PLAYER){
            s = "playing";
        }
        return s;
    }

    public void refresh(GameDTO gameDTO) {
        //todo refresh values


        this.gameDTO = gameDTO;
        refreshValues();
    }


    private void switchToLobby(){
        finish();
    }

    public void onGameChange(GameDTO gameDTO) {
        refresh(gameDTO);
    }
}
