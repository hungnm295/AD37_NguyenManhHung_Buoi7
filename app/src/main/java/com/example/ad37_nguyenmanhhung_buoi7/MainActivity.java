package com.example.ad37_nguyenmanhhung_buoi7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

import static com.example.ad37_nguyenmanhhung_buoi7.R.id.fragment_friend;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friendList = new ArrayList<>();
    ArrayList<Message> messageList = new ArrayList<>();
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Messenger");
        //Start at Friend Fragment
        getFragment(new FriendFragment());

        friendList.add(new Friend("Mr A", true));
        friendList.add(new Friend("Mr B", true));
        friendList.add(new Friend("Mr C", true));
        friendList.add(new Friend("Mr D", true));

        navigationView = findViewById(R.id.navMenu);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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

    public void getFragment(Fragment fragment) {
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

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == Constant.REQUEST_CODE_SMS) {
                    messageList.add(new Message(data.getStringExtra(Constant.NAME_SEND)
                            , data.getStringExtra(Constant.CONTENT)
                            , data.getBooleanExtra(Constant.ICON, true)));
                } else {
                    messageList.add(new Message(data.getStringExtra(Constant.NAME_SEND)
                            , data.getStringExtra(Constant.CONTENT)
                            , data.getBooleanExtra(Constant.ICON, false)));
                }
        }
    }


}

