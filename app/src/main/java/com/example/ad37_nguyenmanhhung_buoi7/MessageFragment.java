package com.example.ad37_nguyenmanhhung_buoi7;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class MessageFragment extends Fragment {

    RelativeLayout mSend;
    RecyclerView rvMessageList;
    MessageAdapter messageAdapter;
    ArrayList<Message> messageList = new ArrayList<>();
    ArrayList<Friend> friendList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMessageList = view.findViewById(R.id.rvMessageList);
        mSend = view.findViewById(R.id.mSend);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList, getActivity());
        rvMessageList.setAdapter(messageAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvMessageList.setLayoutManager(layoutManager);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_send, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mnSMS:
                                if (getActivity() instanceof MainActivity) {
                                    friendList = ((MainActivity) getActivity()).friendList;
                                }
                                Intent intent = new Intent(getActivity(), SendMessage.class);
                                intent.putExtra(Constant.SEND, "SMS");
                                intent.putExtra(Constant.FRIEND_LIST, friendList);
                                startActivityForResult(intent, Constant.REQUEST_CODE_SMS);
                                break;

                            case R.id.mnEmail:
                                if (getActivity() instanceof MainActivity) {
                                    friendList = ((MainActivity) getActivity()).friendList;
                                }
                                Intent intent1 = new Intent(getActivity(), SendMessage.class);
                                intent1.putExtra(Constant.SEND, "email");
                                intent1.putExtra(Constant.FRIEND_LIST, friendList);
                                startActivityForResult(intent1, Constant.REQUEST_CODE_EMAIL);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == Constant.REQUEST_CODE_SMS){
                    messageList.add(new Message(data.getStringExtra(Constant.NAME_SEND)
                            , data.getStringExtra(Constant.CONTENT), true));
                    messageAdapter.notifyDataSetChanged();
                } else{
                    messageList.add(new Message(data.getStringExtra(Constant.NAME_SEND)
                            , data.getStringExtra(Constant.CONTENT), false));
                    messageAdapter.notifyDataSetChanged();
                }
        }
    }
}
