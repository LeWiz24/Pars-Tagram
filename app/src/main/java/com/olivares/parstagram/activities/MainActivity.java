package com.olivares.parstagram.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.olivares.parstagram.R;
import com.olivares.parstagram.fragments.ComposeFragment;
import com.olivares.parstagram.fragments.PostFragment;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //TODO: Update the fragments
                        Toasty.success(MainActivity.this, "Home", Toasty.LENGTH_SHORT, true).show();
                        fragment = new PostFragment();
//                        fragment = fragment1;
                        break;
                    case R.id.action_search:
                        Toasty.success(MainActivity.this, "Search", Toasty.LENGTH_SHORT, true).show();
                        fragment = new ComposeFragment();
//                        fragment = fragment2;
                        break;
                    case R.id.action_compose:
                        Toasty.success(MainActivity.this, "Compose", Toasty.LENGTH_SHORT, true).show();
                        fragment = new ComposeFragment();
//                    fragment = fragment3;
                        break;
                    case R.id.action_likes:
                        Toasty.success(MainActivity.this, "Likes", Toasty.LENGTH_SHORT, true).show();
                        fragment = new ComposeFragment();
//                        fragment = fragment4;
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ComposeFragment();
//                        fragment = fragment5;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);


    }
    // Only menu inflation stuff and maybe not even?

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    // If logout button clicked, use logout method
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnLogout){
            Toasty.success(MainActivity.this, "Your click worked!",Toasty.LENGTH_SHORT,true).show();
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    // Define logout method
    private void logout() {
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                } else {
                    Log.e("TAG", "Error " + e.getMessage());
                }
            }
        });
    }
}