package agh.to2.dicemaster.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import agh.to2.dicemaster.client.api.Server;
import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameDTO;
import agh.to2.dicemaster.common.api.GameType;
import agh.to2.dicemaster.common.api.UserType;
import agh.to2.dicemaster.model.AppState;

public class LobbyActivity extends AppCompatActivity {
    private ArrayList<String> gameListDesc = new ArrayList<String>();
    private List<GameDTO> availableGames;
    private ListView gameList;
    private ArrayAdapter<String> adapter;

    private AppState appState;
    private Server server;

    private String username;
    private String helloBeggining = "Hello, ";
    private String helloEnding = "!";
    private String helloMessage = helloBeggining + "User" + helloEnding;
    private TextView helloTextView;
    private UserType selectedUserType;
    private AlertDialog selectUserTypeAlert;
    private int selectedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_view);

        appState = AppState.appstate;
        this.server = appState.getServer();

        //set hello message
        username = appState.getUserName();
        helloMessage = helloBeggining + username + helloEnding;
        helloTextView = (TextView) findViewById(R.id.text_view_hello);
        helloTextView.setText(helloMessage);

        //set adapter to display the list
        gameList = (ListView) findViewById(R.id.tables_list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gameListDesc);
        gameList.setAdapter(adapter);

        //create a dialog (alert dialog) 3 buttons cancel , observer, player
        //
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.join_table_message)
                .setTitle(R.string.popup_title_table_creator);

        //for positive button set player
        builder.setPositiveButton(R.string.dialog_player, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //set selected user type to player and continue
                selectedUserType = UserType.PLAYER;
                onTableSelect();

            }
        });

        //for neutral button set cancel
        builder.setNeutralButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });

        //for cancel set observer (because the buttons are displayed this way...)
        builder.setNegativeButton(R.string.dialog_observer, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //set selected user type to observer and continue
                selectedUserType = UserType.OBSERVER;
                onTableSelect();
            }
        });

        selectUserTypeAlert = builder.create();
        
        
        //and set listener
        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectedPosition = position;
                selectUserTypeAlert.show();
            }
        });


        //set listener for the buttons
        Button refreshButton = (Button) findViewById(R.id.button_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("buttonRefresh","refreshing game list");
                onRefreshButton();
            }
        });

        Button openTableCreatorButton = (Button) findViewById(R.id.button_create_new_table);
        openTableCreatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("buttonCreate","switching to table creator");
                switchToTableCreator();
            }
        });

        //refresh tables list
        refresh(server.getAvailableGames());

    }

    private void onRefreshButton(){
        refresh(server.getAvailableGames());
    }

    public void refresh(List<GameDTO> availableGames) {
        this.availableGames = availableGames;
        createGamesDesc();
        this.adapter.notifyDataSetChanged();
    }
    private void createGamesDesc() {
        this.gameListDesc.clear();
        for(GameDTO game : availableGames){
            GameConfigDTO gameConfig = game.getGameConfig();
            String gameDesc = "";
            //gameDesc += "GameId:"+game.getId() + ", ";
            gameDesc += "Table name:"+gameConfig.getTableName();
            gameDesc += ",\nGame type:"+gameTypeToString(gameConfig.getGameType());
            gameDesc += ",\nRounds to win:"+gameConfig.getRoundsToWin();
            //gameDesc +=
            //more?

            this.gameListDesc.add(gameDesc);
        }
        //refresh the adapter
        this.adapter.notifyDataSetChanged();
    }

    private String gameTypeToString(GameType gameType){
        switch (gameType) {
            case POKER:
                return "Poker";
            case NPLUS:
                return "N+";
            case NTIMES:
                return "N*";
        }
        return "";
    }


    private void switchToTableCreator(){
        Intent createTable = new Intent(LobbyActivity.this,TableCreatorActivity.class);
        startActivity(createTable);
    }



    private void onTableSelect(){
        GameDTO selectedGameDTO = availableGames.get(selectedPosition);

        int selectedID = 0; //= getSelected?
        ServerGame serverGame = server.requestJoinGame(selectedGameDTO,appState.getGameEventHandler(),selectedUserType);

        if(serverGame != null){
            appState.setUserType(selectedUserType);
            appState.setServerGame(serverGame);
            switchToTable();
        }else{
            onJoinError();
        }
    }

    private void switchToTable(){
        Intent table = new Intent(LobbyActivity.this,TableActivity.class);
        startActivity(table);
    }

    private void onJoinError(){
        //todo - display error couldnt join?
        Log.d("LobbyActivity","incorrect data detected");
    }
}
