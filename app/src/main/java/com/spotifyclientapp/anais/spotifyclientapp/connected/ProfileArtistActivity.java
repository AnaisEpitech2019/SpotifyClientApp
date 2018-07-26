package com.spotifyclientapp.anais.spotifyclientapp.connected;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp.recycler.listArtists.ArtistslistAdapter;
import com.spotifyclientapp.anais.spotifyclientapp.recycler.listTracks.TrackslistAdapter;
import com.spotifyclientapp.anais.spotifyclientapp_api.callbacks.MyCallback;
import com.spotifyclientapp.anais.spotifyclientapp_api.managers.ArtistManager;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.artist.Track;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.modelsAll.AllArtists;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.modelsAll.AllTracks;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.search.Artist;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import okhttp3.Headers;

public class ProfileArtistActivity extends AppCompatActivity {

    private String id_artist;
    private Artist _artist;

    private ImageView _img;
    private TextView _name;

    private List<Track> _list_top_tracks;
    private List<Artist> _list_related_artists;

    private RecyclerView _recyclerArtists;
    private ArtistslistAdapter _adapterArtists;

    private RecyclerView _recyclerTracks;
    private TrackslistAdapter _adapterTracks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_artist);

        _artist = (Artist) getIntent().getSerializableExtra("artist");
        id_artist = getIntent().getStringExtra("id_artist");
        getData();
        setView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        finish();
    }

    public void onBackButtonClicked(View view) {
        finish();
    }

    /*
    ** Set View
    */

    public void setView() {
        // Find ImageView and TextView by ID
        _img = findViewById(R.id.picture);
        _name = findViewById(R.id.name);

        _name.setText(_artist.name);
        // Download png
        if (_artist.images.size() != 0 && _artist.images.get(0).url_icon != null)
            new GetImageFromURL(_img).execute(_artist.images.get(0).url_icon);

    }

    public void setupRecyclerTopTracks() {
        _recyclerTracks = (RecyclerView) findViewById(R.id.list_top_tracks);
        _adapterTracks = new TrackslistAdapter(getApplicationContext(), _list_top_tracks);
        _recyclerTracks.setAdapter(_adapterTracks);
        _recyclerTracks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public void setupRecyclerRelatedArtists() {
        _recyclerArtists = (RecyclerView) findViewById(R.id.list_related_artists);
        _adapterArtists = new ArtistslistAdapter(getApplicationContext(), _list_related_artists);
        _recyclerArtists.setAdapter(_adapterArtists);
        _recyclerArtists.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    /*
    ** Get Data
    */
    public void getData() {
        ArtistManager.getTopTracks(id_artist, getString(R.string.country), new MyCallback<AllTracks>() {
            @Override
            public void onResponseSuccess(AllTracks allTracks, Headers headers) {
                _list_top_tracks = allTracks.list_tracks;
                setupRecyclerTopTracks();
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
                _list_related_artists = allArtists.list_artists;
                setupRecyclerRelatedArtists();
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


    // Download img

    private Bitmap bitmap;

    public class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {

        ImageView img;

        public  GetImageFromURL(ImageView img) {
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urlDisplay = url[0];
            bitmap = null;
            try {
                InputStream src = new URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(src);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null)
                img.setImageBitmap(bitmap);
        }
    }
}
