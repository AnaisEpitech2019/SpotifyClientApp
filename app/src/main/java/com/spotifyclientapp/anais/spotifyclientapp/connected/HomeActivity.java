package com.spotifyclientapp.anais.spotifyclientapp.connected;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp.authentication.ConnectActivity;
import com.spotifyclientapp.anais.spotifyclientapp.recycler.NavigationDrawerAdapter;
import com.spotifyclientapp.anais.spotifyclientapp.utils.AToolbarCompatActivity;
import com.spotifyclientapp.anais.spotifyclientapp_api.managers.APIManager;

import java.util.ArrayList;

public class HomeActivity extends AToolbarCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final int ID_HOME = 0;
    public static final int ID_PROFILE = 1;
    public static final int ID_PLAYLIST = 2;
    public static final int ID_LOGOUT = 3;

    private DrawerLayout _drawerLayout;
    private NavigationDrawerAdapter _drawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent myIntent = getIntent();
        APIManager.setAuthToken(myIntent.getStringExtra("token"));

        //TODO Get la liste des musiques
        setView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        if (_drawerLayout.isDrawerOpen(GravityCompat.START))
            _drawerLayout.closeDrawer(GravityCompat.START);
        else
            _drawerLayout.openDrawer(GravityCompat.START);
    }

    /*
     ** Set View
     */

    public void setView() {

        // setToolbar
        setupToolbar(R.drawable.icon_menu, getString(R.string.app_name));

        // Receive drawer for menu
        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // Init and Set menu
        initNavigationDrawer();
        setupDrawerList();

        //TODO Mettre en place un recycler pour afficher la liste des musiques
    }


    /*
     ** Menu
    */

    private void setupDrawerList() {
        ListView drawerList = (ListView) findViewById(R.id.navigation_view_content);

        _drawerAdapter = new NavigationDrawerAdapter(this, new ArrayList<NavigationDrawerAdapter.MenuElement>());

        _drawerAdapter.addMenuElement(getString(R.string.home), R.string.fa_icon_home, ID_HOME);
        _drawerAdapter.addMenuElement(getString(R.string.profile), R.string.fa_icon_profile, ID_PROFILE);
        _drawerAdapter.addMenuElement(getString(R.string.playlist), R.string.fa_icon_playlist, ID_PLAYLIST);
        _drawerAdapter.addMenuElement(getString(R.string.logout), R.string.fa_icon_logout, ID_LOGOUT);

        drawerList.setAdapter(_drawerAdapter);
        drawerList.setOnItemClickListener(this);
        drawerList.setVerticalScrollBarEnabled(false);
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                _drawerLayout,
                getToolbar(),
                R.string.open,
                R.string.close) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }
        };

        _drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case ID_HOME:
                _drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case ID_PROFILE:
                _drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case ID_PLAYLIST:
                _drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, PlaylistsActivity.class));
                break;
            case ID_LOGOUT:
                _drawerLayout.closeDrawer(GravityCompat.START);
                //TODO Ajouter la liaison de déconnexion
                startActivity(new Intent(this, ConnectActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
