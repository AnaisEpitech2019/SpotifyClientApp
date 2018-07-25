package com.spotifyclientapp.anais.spotifyclientapp.recycler.listCategories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.CategoryMusic;

import java.util.List;

public class CategorieslistAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private Context _context;
    private List<CategoryMusic> _list;

    public CategorieslistAdapter(Context context, List<CategoryMusic> list_categories) {
        _context = context;
        _list = list_categories;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_categories, parent, false);
        return new CategoriesViewHolder(_context, v);    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        holder.bind(_context, _list.get(position));
    }

    @Override
    public int getItemCount() {
        return _list.size();
    }
}
