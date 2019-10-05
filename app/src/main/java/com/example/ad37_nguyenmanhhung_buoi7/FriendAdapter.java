package com.example.ad37_nguyenmanhhung_buoi7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    ArrayList<Friend> friendList;
    Context context;
    IonFriendClick ionFriendClick;

    public FriendAdapter(ArrayList<Friend> friendList, Context context) {
        this.friendList = friendList;
        this.context = context;
    }

    public void setIonFriendClick(IonFriendClick ionFriendClick) {
        this.ionFriendClick = ionFriendClick;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_friend, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, final int position) {
        final Friend friend = friendList.get(position);
        holder.tvName.setText(friend.getName());
        if (friend.isIcon()) {
            holder.imgIcon.setImageResource(R.drawable.ic_facebook);
        }
        holder.tvName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ionFriendClick.onClickName(friend.getName(), position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            imgIcon = itemView.findViewById(R.id.imgIcon);
        }
    }
}
