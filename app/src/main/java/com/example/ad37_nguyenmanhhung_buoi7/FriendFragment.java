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

public class FriendFragment extends Fragment {


    RecyclerView rvFriendList;
    RelativeLayout mAdd;
    FriendAdapter friendAdapter;
    ArrayList<Friend> friendList = new ArrayList<>();
    int mPosition = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFriendList = view.findViewById(R.id.rvFriendList);
        mAdd = view.findViewById(R.id.mAdd);

        if (getActivity() instanceof MainActivity) {
            friendList = ((MainActivity) getActivity()).getFriendList();
            friendAdapter = new FriendAdapter(friendList, getActivity());
            rvFriendList.setAdapter(friendAdapter);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvFriendList.setLayoutManager(layoutManager);

        friendAdapter.setIonFriendClick(new IonFriendClick() {
            @Override
            public void onClickName(final String name, final int position, View v) {
                mPosition = position;
                PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_friend, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mnSendSMS:
                                Intent intent1 = new Intent(getActivity(), SendMessage.class);
                                intent1.putExtra(Constant.SEND, "SMS");
                                intent1.putExtra(Constant.POSITION, position);
                                intent1.putExtra(Constant.FRIEND_LIST, friendList);
                                startActivityForResult(intent1, Constant.REQUEST_CODE_SMS);
                                break;
                            case R.id.mnEdit:
                                Intent intent2 = new Intent(getActivity(), AddUpdateFriend.class);
                                intent2.putExtra(Constant.NAME, name);
                                startActivityForResult(intent2, Constant.REQUEST_CODE_EDIT);
                                break;
                            case R.id.mnDelete:
                                friendList.remove(position);
                                friendAdapter.notifyDataSetChanged();
                        }
                        return false;
                    }
                });
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddUpdateFriend.class);
                intent.putExtra("name", "");
                startActivityForResult(intent, Constant.REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == Constant.REQUEST_CODE_EDIT) {
                    friendList.set(mPosition, new Friend(data.getStringExtra("nameAE")
                            , friendList.get(mPosition).isIcon()));
                    friendAdapter.notifyDataSetChanged();
                } else if (requestCode == Constant.REQUEST_CODE_ADD) {
                    friendList.add(new Friend(data.getStringExtra("nameAE"), true));
                    friendAdapter.notifyDataSetChanged();
                }
        }
    }
}
