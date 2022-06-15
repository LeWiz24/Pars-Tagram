package com.olivares.parstagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.olivares.parstagram.adapters.PostsAdapter;
import com.olivares.parstagram.fragments.ComposeFragment;
import com.olivares.parstagram.fragments.PostFragment;
import com.olivares.parstagram.models.Post;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
                } else {
                    Log.e("TAG", "Error " + e.getMessage());
                }
            }
        });
    }
}