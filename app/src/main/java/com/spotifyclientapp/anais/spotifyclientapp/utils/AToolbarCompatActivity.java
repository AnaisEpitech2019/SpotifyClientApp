package com.spotifyclientapp.anais.spotifyclientapp.utils;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.spotifyclientapp.anais.spotifyclientapp.R;

public abstract class AToolbarCompatActivity extends AppCompatActivity {

    private static final String TAG = "AToolbarCompatActivity";

    private Toolbar _toolbar;
    private TextView _toolbarTitle;

    protected void setupToolbar(int homeIconId, String title) {
        setupToolbar(homeIconId);
        setToolbarTitle(title);
    }

    @SuppressLint("RestrictedApi")
    public void setupToolbar(int homeIconId) {
        _toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (_toolbar != null)
        {
            _toolbarTitle = (TextView) _toolbar.findViewById(R.id.toolbar_title);
            setSupportActionBar(_toolbar);
        }
        if (homeIconId != 0) {
            try {
                getSupportActionBar().setHomeAsUpIndicator(homeIconId);
                _toolbar.setNavigationIcon(homeIconId);
            } catch (NullPointerException e) {
                Log.d(TAG, "Error setting home indicator : " + e.getMessage());
            }
        }

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void setToolbarTitle(String toolbarTitle) {
        if (toolbarTitle != null) _toolbarTitle.setText(toolbarTitle);
    }

    protected Toolbar getToolbar() {
        return _toolbar;
    }
}

