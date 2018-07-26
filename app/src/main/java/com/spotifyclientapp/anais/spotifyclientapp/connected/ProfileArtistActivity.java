package com.spotifyclientapp.anais.spotifyclientapp.connected;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.callbacks.MyCallback;
import com.spotifyclientapp.anais.spotifyclientapp_api.managers.ArtistManager;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.modelsAll.AllArtists;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.modelsAll.AllTracks;

import okhttp3.Headers;

public class ProfileArtistActivity extends AppCompatActivity {

    private String id_artist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_artist);

        id_artist = getIntent().getStringExtra("id_artist");
        getData();
    }

    /*
    ** Get Data
    */
    public void getData() {
        ArtistManager.getTopTracks(id_artist, getString(R.string.country), new MyCallback<AllTracks>() {
            @Override
            public void onResponseSuccess(AllTracks allTracks, Headers headers) {
                //TODO Set first recycler
            }

            @Override
            public void onResponseFailure(String s, Error error, int i) {
                Toast.makeText(getApplicationContext(), getString(R.string.global_error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(getApplicationContext(), getString(R.string.global_error), Toast.LENGTH_SHORT).show();
            }
        });

        ArtistManager.getRelatedArtists(id_artist, new MyCallback<AllArtists>() {
            @Override
            public void onResponseSuccess(AllArtists allArtists, Headers headers) {
                //TODO Set second recycler
            }

            @Override
            public void onResponseFailure(String s, Error error, int i) {
                Toast.makeText(getApplicationContext(), getString(R.string.global_error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(getApplicationContext(), getString(R.string.global_error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
