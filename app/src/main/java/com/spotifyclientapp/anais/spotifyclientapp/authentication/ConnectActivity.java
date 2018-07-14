package com.spotifyclientapp.anais.spotifyclientapp.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp.connected.HomeActivity;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*
    ** Buttons
    */

    public void onFbButtonClicked(View view) {
        //TODO Liaison API de connexion via Fb
    }

    public void onConnectButtonClicked(View view) {
        //TODO Vérifications à faire
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void onForgotPwdButtonClicked(View view) {
        //TODO Ajouter la page d'oubli de mdp
    }
}
