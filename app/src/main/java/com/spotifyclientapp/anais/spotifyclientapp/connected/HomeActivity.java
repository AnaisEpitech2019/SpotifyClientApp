package com.spotifyclientapp.anais.spotifyclientapp.connected;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.spotifyclientapp.anais.spotifyclientapp.R;
import com.spotifyclientapp.anais.spotifyclientapp.authentication.ConnectActivity;
import com.spotifyclientapp.anais.spotifyclientapp.recycler.NavigationDrawerAdapter;
import com.spotifyclientapp.anais.spotifyclientapp.recycler.listCategories.CategorieslistAdapter;
import com.spotifyclientapp.anais.spotifyclientapp.utils.AToolbarCompatActivity;
import com.spotifyclientapp.anais.spotifyclientapp_api.callbacks.MyCallback;
import com.spotifyclientapp.anais.spotifyclientapp_api.managers.HomeManager;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.CategoryMusic;
import com.spotifyclientapp.anais.spotifyclientapp_api.models.modelsAll.AllCategories;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class HomeActivity extends AToolbarCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final int ID_HOME = 0;
    public static final int ID_PROFILE = 1;
    public static final int ID_PLAYLIST = 2;
    public static final int ID_LOGOUT = 3;

    private DrawerLayout _drawerLayout;
    private NavigationDrawerAdapter _drawerAdapter;

    private RecyclerView _recycler;
    private CategorieslistAdapter _adapter;

    private List<CategoryMusic> _list_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getData("0", "20");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("HOME ACTIVITY", "Query : " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
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
    ** Get Data : Categories
    */

    public void getData(String offset, String limit) {
        HomeManager.getAllCategories(offset, limit, new MyCallback<AllCategories>() {
            @Override
            public void onResponseSuccess(AllCategories allCategories, Headers headers) {
                if (_list_categories == null)
                    _list_categories = allCategories.info_categories.list_categories;
                else
                    _list_categories.addAll(allCategories.info_categories.list_categories);

                if (allCategories.info_categories.next != null && allCategories.info_categories.next != "") {
                    String[] parts = allCategories.info_categories.next.split("=|&");
                    getData(parts[1], parts[3]);
                }
                setView();
            }

            @Override
            public void onResponseFailure(String s, Error error, int i) {
                Log.d("HOME ACTIVITY", "ERROR 1 : " + s);
                setView();
            }

            @Override
            public void onFailure(String s) {
                Log.d("HOME ACTIVITY", "ERROR 2 : " + s);
                setView();
            }
        });
    }

    /*
    ** Set View
    */

    Toolbar _toolbar;

    public void setView() {

        // setToolbar
        setupToolbar(R.drawable.icon_menu, "");
        _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);

        // Receive drawer for menu
        _drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // Init and Set menu
        initNavigationDrawer();
        setupDrawerList();

        if (_list_categories != null && _list_categories.size() > 0) {
            setupRecyclerCategories();
        }
    }

    public void setupRecyclerCategories() {
        _recycler = (RecyclerView) findViewById(R.id.list_item);
        _adapter = new CategorieslistAdapter(getApplicationContext(), _list_categories);
        _recycler.setAdapter(_adapter);
        _recycler.setLayoutManager(new GridLayoutManager(this, 2));
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
                startActivity(new Intent(this, ConnectActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}
