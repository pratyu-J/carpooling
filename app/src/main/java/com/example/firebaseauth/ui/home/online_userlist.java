package com.example.firebaseauth.ui.home;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firebaseauth.R;
import com.example.firebaseauth.Users;

import java.util.List;

public class online_userlist extends ArrayAdapter<Users> {
    private Activity context;
    private List<Users> online_userslist;

    public online_userlist(Activity context,List<Users> online_userslist){
        super(context, R.layout.list_layout_home,online_userslist);
        this.context=context;
        this.online_userslist=online_userslist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater_home=context.getLayoutInflater();
        View listviewItem_home=inflater_home.inflate(R.layout.list_layout_home,null,true);

        TextView name_home=(TextView)listviewItem_home.findViewById(R.id.username_home);
        TextView mobile_home=(TextView)listviewItem_home.findViewById(R.id.user_mobile_home);
        Users users=online_userslist.get(position);

        name_home.setText(users.getUname());
        mobile_home.setText(users.getUrollno());

        return listviewItem_home;
    }

}
