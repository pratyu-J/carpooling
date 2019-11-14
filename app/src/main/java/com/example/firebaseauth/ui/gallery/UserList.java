package com.example.firebaseauth.ui.gallery;

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

public class UserList extends ArrayAdapter<Users> {

    private Activity context;
    private List<Users> usersList;

    public UserList(Activity context,List<Users> usersList){
        super(context, R.layout.list_layout_gallery,usersList);
        this.context=context;
        this.usersList=usersList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater_gallery=context.getLayoutInflater();
        View listviewItem_gallery=inflater_gallery.inflate(R.layout.list_layout_gallery,null,true);

        TextView name_gallery=(TextView) listviewItem_gallery.findViewById(R.id.username_gallery);
        TextView mobile_gallery=(TextView)listviewItem_gallery.findViewById(R.id.user_mobile_gallery);
        Users users=usersList.get(position);

        name_gallery.setText(users.getUname());
        mobile_gallery.setText(users.getUrollno());

        return listviewItem_gallery;
    }
}
