package com.spotifyclientapp.anais.spotifyclientapp.recycler.listTracks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.artist.Track;

import java.io.InputStream;
import java.net.URL;

public class TracksViewHolder extends RecyclerView.ViewHolder {

    private Context _context;

    private ImageView _img;
    private TextView _song_title;
    private TextView _album_title;

    public TracksViewHolder(Context context, View itemView) {
        super(itemView);

        _context = context;

        // Find ImageView and TextView by ID
        _img = itemView.findViewById(R.id.img);
        _song_title = itemView.findViewById(R.id.song_title);
        _album_title = itemView.findViewById(R.id.album_title);
    }

    public void bind(Context context, final Track track) {
        // Set TextView and icon
        _song_title.setText(track.name);
        _album_title.setText(track.album.name);

        // Download png
        if (track.album.list_img.get(0).url_icon != "" && track.album.list_img.get(0).url_icon != null)
            new TracksViewHolder.GetImageFromURL(_img).execute(track.album.list_img.get(0).url_icon);
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
