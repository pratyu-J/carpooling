package com.example.firebaseauth.ui.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RcViewHome extends RecyclerView.Adapter<RcViewHome.RcViewHolder> {

    private String[] data;
    public RcViewHome(String[] data) {
        this.data=data;
    }

    @NonNull
    @Override
    public RcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RcViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RcViewHolder extends RecyclerView.ViewHolder{

        public RcViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
