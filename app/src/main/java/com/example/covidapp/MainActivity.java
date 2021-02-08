package com.example.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to Bottom Navigation View
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_bar);

        //Listener is passed to Bottom Navigation
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Start App in Home Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    //Create Listener to allow for reacting to clicks on the items in the Bottom Nav View
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //the above parameter item refers to the item selected in Bottom Nav View

                    //initializing selectedFragment variable
                    Fragment selectedFragment = null;

                    /*
                     * Depending on what item is selected, the selected fragment changed to the
                     * one of the three available fragments
                     */
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_map:
                            selectedFragment = new MapFragment();
                            break;
                        case R.id.nav_help:
                            selectedFragment = new HelpFragment();
                            break;
                    }

                    //Show the selected Fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    /*
                     * Returning true means the item shows as selected. Returning false would still
                     * show the Fragment, but the item in Nav would not be selected.
                     */
                    return true;
                }
            };

}