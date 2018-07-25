package com.spotifyclientapp.anais.spotifyclientapp.recycler.listArtists;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.search.Artist;

import java.io.InputStream;
import java.net.URL;

public class ArtistsViewHolder extends RecyclerView.ViewHolder {

    private Context _context;

    private Bitmap bitmap;

    private ImageView _img;
    private TextView _name;
    private TextView _nb_followers;

    public ArtistsViewHolder(Context context, View itemView) {
        super(itemView);

        _context = context;

        // Find ImageView and TextView by ID
        _img = itemView.findViewById(R.id.img);
        _name = itemView.findViewById(R.id.name_artist);
        _nb_followers = itemView.findViewById(R.id.nb_followers);
    }

    public void bind(Context context, final Artist artist) {
        // Set TextView and icon
        _name.setText(artist.name);
        _nb_followers.setText(String.valueOf(artist.followers.nb_followers));

        Log.d("ArtistViewHolder", "list_img.size : " + String.valueOf(artist.images.size()));
        // Download png
        if (artist.images.size() != 0 && artist.images.get(0).url_icon != null)
            new GetImageFromURL(_img).execute(artist.images.get(0).url_icon);

        // Select artist
        itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO Uncomment
            // Send information at the next activity
            //Intent intent = new Intent(_context, ProfileArtistActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.putExtra("id_artist", artist.id);
            //_context.startActivity(intent);
            }
        });
    }

    // Download img
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
