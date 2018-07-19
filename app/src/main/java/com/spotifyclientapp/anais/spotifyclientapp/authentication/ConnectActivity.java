package com.spotifyclientapp.anais.spotifyclientapp.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Spotify;
import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp.connected.HomeActivity;

public class ConnectActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "def1d7544af7483eb15d2df90913a699";
    private static final String REDIRECT_URI = "spotifyclientapp://callback";
    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            switch (response.getType()) {
                case TOKEN:
                    Log.d("ConnectActivity", "SUCCESS : " + response.getAccessToken());
                    Intent myIntent = new Intent(this, HomeActivity.class);
                    myIntent.putExtra("token",response.getAccessToken());
                    startActivity(myIntent);
                    break;

                case ERROR:
                    Toast.makeText(this, getString(R.string.global_error), Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(this, getString(R.string.global_error), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
