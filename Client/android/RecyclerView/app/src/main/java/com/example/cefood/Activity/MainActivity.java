package com.example.cefood.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
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
                    case R.id.nav_item_cart:
                        Intent intentToCart = new Intent(MainActivity.this, CartActivity.class);
                        startActivity(intentToCart);
                        break;
                    case R.id.nav_item_logout:
                        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
                        sharedPreferences.edit().remove("accessToken").apply();
                        sharedPreferences.edit().remove("userName").apply();
                        sharedPreferences.edit().remove("userPhone").apply();
                        Intent intentToLogin = new Intent(MainActivity.this, LoginActivity.class);
                        intentToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentToLogin);
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

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };
}
