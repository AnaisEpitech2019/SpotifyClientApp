package com.spotifyclientapp.anais.spotifyclientapp.recycler.listCategories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.CategoryMusic;

import java.io.InputStream;
import java.net.URL;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    private Context _context;

    private Bitmap bitmap;

    private TextView _name;
    private ImageView _img;

    public CategoriesViewHolder(final Context context, View itemView) {
        super(itemView);

        _context = context;
        _name = (TextView) itemView.findViewById(R.id.name_category);
        _img = (ImageView) itemView.findViewById(R.id.img);
    }

    public void bind(final Context context, final CategoryMusic category) {
        _name.setText(category.name);
        // Download png
        if (category.icons.get(0).url_icon != "")
            new GetImageFromURL(_img).execute(category.icons.get(0).url_icon);

        //TODO Uncomment if selectable
        /*
        // Selection Course
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send information at the next activity
                Intent intent = new Intent(_context, FactSheetCategoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_equipment", category.id);
                _context.startActivity(intent);
            }
        });
        */
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

            img.setImageBitmap(bitmap);
        }
    }
}

