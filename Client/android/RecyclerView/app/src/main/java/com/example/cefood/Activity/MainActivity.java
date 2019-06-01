package com.example.cefood.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cefood.Fragments.HistoryPageFragment;
import com.example.cefood.Fragments.HomePageFragment;
import com.example.cefood.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    Fragment fragment = null;

    private enum NavigationFragment {
        Home,
        History
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mdrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new HomePageFragment();
        if (fragment != null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        mdrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue));
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menu);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_item_mainview:
                        ChangeFragment(NavigationFragment.Home);
                        break;
                    case R.id.nav_item_history:
                        ChangeFragment(NavigationFragment.History);
                        break;
                    default:
                        return true;
                }

                menuItem.setChecked(true);
                mdrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void ChangeFragment(NavigationFragment value) {
        switch (value) {
            case Home:
                fragment = new HomePageFragment();
                break;
            case History:
                fragment = new HistoryPageFragment();
                break;
        }
        if (fragment != null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
    }
}
