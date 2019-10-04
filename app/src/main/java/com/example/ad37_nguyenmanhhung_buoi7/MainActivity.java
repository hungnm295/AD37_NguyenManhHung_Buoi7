package com.example.ad37_nguyenmanhhung_buoi7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend>  friendList;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Messenger");

        friendList = new ArrayList<>();
        friendList.add(new Friend("Mr A", true));
        friendList.add(new Friend("Mr B", true));
        friendList.add(new Friend("Mr C", true));
        friendList.add(new Friend("Mr D", true));

        getFragment(new FriendFragment());

        navigationView = findViewById(R.id.navMenu);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mnFriends:
                        getFragment(new FriendFragment());
                        break;
                    case R.id.mnMessages:
                        getFragment(new MessageFragment());
                        break;
                }
                return false;
            }
        });
    }

    public void getFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Friend> getFriendList() {
        return friendList;
    }

}

