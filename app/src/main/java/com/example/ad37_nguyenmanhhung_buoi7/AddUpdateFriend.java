package com.example.ad37_nguyenmanhhung_buoi7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUpdateFriend extends AppCompatActivity {
    TextView tvName;
    EditText edtName;
    Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_friend);
        initView();
        Intent intent = getIntent();
        String mName = intent.getStringExtra("name");
        if (mName.equals("")) {
            tvName.setText("Add New Friend");
            btnAction.setText("Add");
            setTitle("Add Friend");
        } else {
            tvName.setText("Edit Friend");
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
                    setResult(RESULT_OK, intent1);
                    finish();
                } else {
                    Toast.makeText(AddUpdateFriend.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initView() {
        tvName = findViewById(R.id.tvName);
        edtName = findViewById(R.id.edtName);
        btnAction = findViewById(R.id.btnAction);
    }
}
