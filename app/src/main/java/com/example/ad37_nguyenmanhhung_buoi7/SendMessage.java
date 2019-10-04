package com.example.ad37_nguyenmanhhung_buoi7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SendMessage extends AppCompatActivity {
    TextView tvSendType;
    Spinner spFriend;
    Button btnAction;
    EditText edtContent;
    ArrayList<Friend> friendList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        initView();

        final Intent intent = getIntent();
        int position = intent.getIntExtra(Constant.POSITION, 1);
        String send = intent.getStringExtra(Constant.SEND);
        if (send.equals("SMS")) {
            tvSendType.setText("Send SMS");
        } else {
            tvSendType.setText("Send Email");
        }

        //Chuyển list Friend sang
        friendList = (ArrayList<Friend>) intent
                .getSerializableExtra(Constant.FRIEND_LIST);
        for (Friend friend : friendList) {
            nameList.add(friend.getName());
        }

        //Set spinner
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext()
                , android.R.layout.simple_list_item_1, nameList);
        spFriend.setAdapter(adapter);
        spFriend.setSelection(position);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                String sendName = spFriend.getSelectedItem().toString();
                String content = edtContent.getText().toString();
                intent1.putExtra(Constant.NAME_SEND, sendName);
                intent1.putExtra(Constant.CONTENT, content);
                setResult(RESULT_OK, intent1);
                getFragment(new MessageFragment());
            }
        });

    }

    public void initView() {
        tvSendType = findViewById(R.id.tvSendType);
        spFriend = findViewById(R.id.spFriend);
        btnAction = findViewById(R.id.btnAction);
        edtContent = findViewById(R.id.edtContent);
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
}
