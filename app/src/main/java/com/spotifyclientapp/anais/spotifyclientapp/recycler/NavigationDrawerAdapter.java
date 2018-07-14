package com.spotifyclientapp.anais.spotifyclientapp.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp.utils.FontManager;

import java.util.List;

public class NavigationDrawerAdapter extends ArrayAdapter<NavigationDrawerAdapter.MenuElement> {

    private Context _context;
    private List<MenuElement> _items;

    public NavigationDrawerAdapter(@NonNull Context context, List<MenuElement> list) {
        super(context, R.layout.content_navigation_drawer, list);

        _context = context;
        _items = list;
    }

    public void addMenuElement(String content, int iconId, int id) {
        _items.add(new MenuElement(content, iconId, id));
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView;

        MenuElement element = _items.get(position);

        rowView = convertView != null ? convertView : inflater.inflate(R.layout.content_navigation_drawer, parent, false);

        ((TextView) rowView.findViewById(R.id.text)).setText(element._content);

        Typeface iconFont = Typeface.createFromAsset(_context.getAssets(), FontManager.FONTAWESOME);

        TextView icon = (TextView) rowView.findViewById(R.id.icon);
        icon.setTypeface(iconFont);
        icon.setText(element._iconId);

        return rowView;
    }

    public class MenuElement {
        String _content;
        int _iconId = 0;
        int _id = -1;

        public MenuElement(String content, int iconId, int id) {
            _content = content;
            _iconId = iconId;
            _id = id;
        }
    }
}
