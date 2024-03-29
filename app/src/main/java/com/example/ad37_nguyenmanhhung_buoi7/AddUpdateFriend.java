package com.example.ad37_nguyenmanhhung_buoi7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUpdateFriend extends AppCompatActivity {
    EditText edtName;
    Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_friend);
        initView();

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#66B8F5")));

        Intent intent = getIntent();
        String mName = intent.getStringExtra("name");

        if (mName.equals("")) {
            btnAction.setText("Add");
            setTitle("Add New Friend");
        } else {
            btnAction.setText("Update");
            edtName.setText(mName);
            setTitle("Edit Friend");
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent();
                String name = edtName.getText().toString();
                if (!name.isEmpty()) {
                    intent1.putExtra("nameAE", name);
                    setResult(Constant.RESULT_FRIEND, intent1);
                    finish();
                } else {
                    Toast.makeText(AddUpdateFriend.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initView() {
        edtName = findViewById(R.id.edtName);
        btnAction = findViewById(R.id.btnAction);
    }
}
