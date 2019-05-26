package com.example.recyclerview;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.recyclerview.Fragment.HistoryPageFragment;
import com.example.recyclerview.Fragment.HomePageFragment;
import com.example.recyclerview.Server.UserNetworkProvider;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mdrawerLayout;
    Fragment fragment = null;
//    HomePageFragment homePageFragment;
//    FragmentTransaction fragmentTransaction;
//    HistoryPageFragment historyPageFragment;
//    FragmentManager fragmentManager;
    private enum NavigationFragment{
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
        if(fragment!=null)
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
                switch(id)
                {
                    case R.id.nav_item_mainview:
                        Toast.makeText(MainActivity.this, "Main View",Toast.LENGTH_SHORT).show();
                        ChangeFragment(NavigationFragment.Home);
//                        fragmentManager = getSupportFragmentManager();
//                        homePageFragment = new HomePageFragment();
//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.content_frame,homePageFragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();

                        break;
                    case R.id.nav_item_history:
                        Toast.makeText(MainActivity.this, "History",Toast.LENGTH_SHORT).show();
                        ChangeFragment(NavigationFragment.History);
//                        historyPageFragment = new HistoryPageFragment();
//                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.content_frame,historyPageFragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();
                        break;
                    default:
                        return true;
                }


                // set item as selected to persist highlight
                menuItem.setChecked(true);
                // close drawer when item is tapped
                mdrawerLayout.closeDrawers();

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                return true;
            }
        });
    }

    private void ChangeFragment(NavigationFragment value){
        switch (value) {
            case Home:    fragment = new HomePageFragment();
            break;
            case History: fragment = new HistoryPageFragment();
            break;
        }
        if(fragment!=null)
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

}
