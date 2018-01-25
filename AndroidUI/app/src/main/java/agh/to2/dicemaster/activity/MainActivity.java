package agh.to2.dicemaster.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import agh.to2.dicemaster.client.api.Server;
import agh.to2.dicemaster.mock.serverMock;
import agh.to2.dicemaster.model.AppState;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //main activity that starts with the app
        new AppState(this);
        Server server = new serverMock();   //todo  Server.createDiceMasterServer("serverAddress"," "," ");
        boolean serverOK = true;            //todo check if the server connection is fine?
        AppState.appstate.initServer(server);
        if(serverOK){
            switchToLogin();
        }else{
            onServerError();
        }
    }

    private void switchToLogin(){
        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);
    }

    private void onServerError(){
        //todo -  some error message?
        finish();
    }
}
