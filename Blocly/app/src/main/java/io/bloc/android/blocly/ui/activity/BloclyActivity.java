package io.bloc.android.blocly.ui.activity;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import io.bloc.android.blocly.R;
import io.bloc.android.blocly.ui.adapter.ItemAdapter;
import io.bloc.android.blocly.ui.adapter.NavigationDrawerAdapter;

/**
 * Created by Kari on 10/5/2015.
 */
public class BloclyActivity extends AppCompatActivity {

    private ItemAdapter itemAdapter;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationDrawerAdapter navigationDrawerAdapter;
    private Menu menu;
    private View overflowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocly);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_blocly);
        setSupportActionBar(toolbar);

        itemAdapter = new ItemAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_activity_blocly);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_activity_blocly);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0,0){

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (overflowButton == null){
                    ArrayList<View> foundViews = new ArrayList<View>();
                    getWindow().getDecorView().findViewsWithText(foundViews,
                            getString(R.string.abc_action_menu_overflow_description),
                            View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
                    if (foundViews.size() > 0){
                        overflowButton = foundViews.get(0);
                    }
                }
                if (overflowButton != null){
                    overflowButton.setAlpha(1f - slideOffset);
                }
                if (menu == null)
                    return;

                for (int i=0; i < menu.size(); i++){
                    MenuItem item = menu.getItem(i);
                    Drawable icon = item.getIcon();
                    if ( icon!= null){
                        icon.setAlpha((int)(1f -slideOffset) * 255);
                    }
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu();
                if (overflowButton != null){
                    overflowButton.setEnabled(false);
                }
                if (menu == null){
                    return;
                }
                for (int i=0; i < menu.size(); i++){
                    menu.getItem(i).setEnabled(false);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                invalidateOptionsMenu();
                if (overflowButton != null){
                    overflowButton.setAlpha(1F);
                    overflowButton.setEnabled(true);
                }
                if (menu == null) {
                    return;
                }
                for (int i=0; i < menu.size(); i++){
                    MenuItem item = menu.getItem(i);
                    item.setEnabled(true);
                    Drawable icon = item.getIcon();
                    if (icon != null){
                        icon.setAlpha(255);
                    }
                }
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        navigationDrawerAdapter = new NavigationDrawerAdapter();
        RecyclerView navigationRecyclerView = (RecyclerView) findViewById(R.id.rv_nav_activity_blocly);
        navigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        navigationRecyclerView.setItemAnimator(new DefaultItemAnimator());
        navigationRecyclerView.setAdapter(navigationDrawerAdapter);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.blocly, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String menuToast = "";
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        if (item.getTitle().equals(getString(R.string.menu_share)))
            menuToast = "I love to Share";
        else if (item.getTitle().equals(getString(R.string.menu_search)))
            menuToast = "Searching is Fun";
        else if (item.getTitle().equals(getString(R.string.menu_refresh)))
            menuToast = "Refresh it up";
        else if (item.getTitle().equals(getString(R.string.menu_mark_as_read)))
            menuToast = "Mark 'em all!";

        Toast.makeText(this, menuToast, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
