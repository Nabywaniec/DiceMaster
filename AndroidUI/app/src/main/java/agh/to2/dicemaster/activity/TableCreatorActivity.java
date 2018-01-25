package agh.to2.dicemaster.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import agh.to2.dicemaster.client.api.ServerGame;
import agh.to2.dicemaster.common.api.GameConfigDTO;
import agh.to2.dicemaster.common.api.GameType;
import agh.to2.dicemaster.common.api.UserType;
import agh.to2.dicemaster.model.AppState;

public class TableCreatorActivity extends AppCompatActivity {
    //this activity is not kept in the history stack!
    // set in android manifest - android:noHistory = "true"
    // this can be dynamically changed setting FLAG_ACTIVITY_NO_HISTORY

    private AppState appState;

    private static int min_MaxPlayers = 2;
    private static int min_RoundsToWin = 1;
    private static int min_EasyBots = 0;
    private static int min_HardBots = 0;

    private UserType selectedUserType;
    private String selectedGameName;
    private GameType selectedGameType;
    private int selectedMaxPlayers;
    private int selectedRoundsToWin;
    private int selectedEasyBotsCount;
    private int selectedHardBotsCount;

    private EditText mGameName;
    private EditText mMaxPlayers;
    private EditText mRoundToWin;
    private EditText mEasyBotsCount;
    private EditText mHardBotsCount;
    private Spinner mGameType;

    private ArrayAdapter<CharSequence> adapter;

    private AlertDialog selectUserTypeAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_creator_view);

        this.appState = AppState.appstate;

        mGameName = (EditText) findViewById(R.id.editText_game_name);
        mMaxPlayers = (EditText) findViewById(R.id.editText_max_players);
        mRoundToWin = (EditText) findViewById(R.id.editText_rounds_to_win);
        mEasyBotsCount = (EditText) findViewById(R.id.editText_easy_bots_count);
        mHardBotsCount = (EditText) findViewById(R.id.editText_hard_bots_count);
        mGameType = (Spinner) findViewById(R.id.spinner_game_type);

        //set spinner to display options
        this.adapter = ArrayAdapter.createFromResource(this,
                R.array.game_types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGameType.setAdapter(adapter);


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
                createTable();
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
                createTable();
            }
        });

        selectUserTypeAlert = builder.create();


        //set go back button listener
        Button goBackButton = (Button) findViewById(R.id.button_go_back);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLobby();
            }
        });

        //set confirm and create table button listener
        Button confirmButton = (Button) findViewById(R.id.button_confirm_and_create_table);
        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //display dialog - create and join as: player, observer?
                selectUserTypeAlert.show();
            }
        });

    }

    private GameType gameTypeFromString(String item){
        GameType gt = null;
        String poker = getString(R.string.poker_name);
        String nplus = getString(R.string.nplus_name);
        String ntimes = getString(R.string.ntimes_name);

        if(item.equals(poker)) {
            gt = GameType.POKER;
        }else if (item.equals(nplus)){
            gt = GameType.NPLUS;
        }else if (item.equals(ntimes)){
            gt = GameType.NTIMES;
        }else {
            Log.d("TableCreatorActivity","unrecognized game type");
        }

        return gt;
    }

    private void createTable(){
        //read data
        selectedGameName = mGameName.getText().toString();      //read selected game name
        String item = mGameType.getSelectedItem().toString();   //read selected user type from spinner
        selectedGameType = gameTypeFromString(item);            //get selected game type from string
        selectedRoundsToWin = Integer.parseInt(mRoundToWin.getText().toString());
        selectedMaxPlayers = Integer.parseInt(mMaxPlayers.getText().toString());
        selectedEasyBotsCount = Integer.parseInt(mEasyBotsCount.getText().toString());
        selectedHardBotsCount = Integer.parseInt(mHardBotsCount.getText().toString());

        //check data
        if(   !(selectedMaxPlayers >= min_MaxPlayers &&
                selectedRoundsToWin >= min_RoundsToWin &&
                selectedEasyBotsCount >= min_EasyBots &&
                selectedHardBotsCount >= min_HardBots &&
                selectedUserType != null &&
                selectedGameType != null
        )){onIncorrectData();}
        else{
            GameConfigDTO newTableConfig =
                    new GameConfigDTO(selectedGameName,selectedMaxPlayers,selectedGameType,selectedHardBotsCount,selectedEasyBotsCount,selectedRoundsToWin);
            ServerGame newServerGame = appState.getServer().createGame(newTableConfig,appState.getGameEventHandler(),selectedUserType);
            if(newServerGame != null){
                appState.setUserType(selectedUserType);
                appState.setServerGame(newServerGame);
                switchToTable();
            }else{
                onCreateTableError();
            }
        }
    }


    private void switchToLobby(){
        //just go back
        finish();
    }

    private void switchToTable(){
        Intent table = new Intent(TableCreatorActivity.this,TableActivity.class);
        startActivity(table);
    }

    private void onIncorrectData(){
        //todo display incorrect data message
        Log.d("TableCreatorActivity","incorrect data detected");
    }

    private void onCreateTableError(){

        //todo display error message - incorrect data
        Log.d("TableCreatorActivity","error creating table");

    }
}
