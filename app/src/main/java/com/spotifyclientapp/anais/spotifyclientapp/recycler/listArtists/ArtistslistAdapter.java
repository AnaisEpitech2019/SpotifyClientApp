package com.spotifyclientapp.anais.spotifyclientapp.recycler.listArtists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.search.Artist;

import java.util.List;

public class ArtistslistAdapter extends RecyclerView.Adapter<ArtistsViewHolder> {

    private Context _context;
    private List<Artist> _list;

    public ArtistslistAdapter(Context context, List<Artist> list_artists) {
        _context = context;
        _list = list_artists;
    }

    @Override
    public ArtistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_artists, parent, false);
        return new ArtistsViewHolder(_context, v);    }

    @Override
    public void onBindViewHolder(ArtistsViewHolder holder, int position) {
        holder.bind(_context, _list.get(position));
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }
}
