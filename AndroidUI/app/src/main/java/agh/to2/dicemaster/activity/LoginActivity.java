package agh.to2.dicemaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import agh.to2.dicemaster.model.AppState;

public class LoginActivity extends AppCompatActivity {

    private EditText nickNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);


        this.nickNameText = (EditText) findViewById(R.id.edit_text_nickname);

        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClick();
            }
        });
    }

    private void onLoginButtonClick(){
        String userName = this.nickNameText.getText().toString();
        Log.d("loginButton","username="+ userName);     //send log on debug

        AppState appstate = AppState.appstate;
        boolean loginOK = appstate.getServer().registerClient(userName);    //try to login
        if(loginOK){
            appstate.setUserName(userName);
            switchToLobby();
        }else{
            onLoginError();
        }
    }

    private void switchToLobby(){
        Intent lobby = new Intent(this,LobbyActivity.class);
        startActivity(lobby);
    }

    private void onLoginError(){
        //todo - display login error
    }
}
