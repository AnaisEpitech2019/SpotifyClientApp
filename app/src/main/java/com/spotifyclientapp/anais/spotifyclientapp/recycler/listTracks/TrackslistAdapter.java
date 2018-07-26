package com.spotifyclientapp.anais.spotifyclientapp.recycler.listTracks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.artist.Track;

import java.util.List;

public class TrackslistAdapter extends RecyclerView.Adapter<TracksViewHolder> {

    private Context _context;
    private List<Track> _list;

    public TrackslistAdapter(Context context, List<Track> list_tracks) {
        _context = context;
        _list = list_tracks;
    }

    @Override
    public TracksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tracks, parent, false);
        return new TracksViewHolder(_context, v);    }

    @Override
    public void onBindViewHolder(TracksViewHolder holder, int position) {
        holder.bind(_context, _list.get(position));
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }
}
