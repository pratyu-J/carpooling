package com.example.firebaseauth.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauth.Commuters;
import com.example.firebaseauth.R;
import com.example.firebaseauth.Users;
import com.example.firebaseauth.ui.gallery.UserList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    DatabaseReference databaseReference_home;
    Spinner spinner_home;
    Button button1;
    private List<Users> userlist;
    ListView list_home;
    Users users;

    String[] destination={"Botanical Garden","Sector 62","Sector 128","Sector 18","Noida City Center"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
       // recyclerView_home=root.findViewById(R.id.home_rc_view);
        list_home=root.findViewById(R.id.listview_home);
        databaseReference_home= FirebaseDatabase.getInstance().getReference("Users");

        userlist=new ArrayList<>();
        users =new Users();

        button1=root.findViewById(R.id.btn_home);
       spinner_home=root.findViewById(R.id.h0me_spinner_id);
       ArrayAdapter<String> arrayAdapter_home=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,destination);
       spinner_home.setAdapter(arrayAdapter_home);
       spinner_home.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               final int pos=spinner_home.getSelectedItemPosition();
               //updating user destination

               String uid=users.getUserid();

               users.setDestiny(destination[pos]);
               databaseReference_home.child(uid).setValue(users);

               button1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Toast.makeText(getActivity(),"Finding commuters for "+destination[pos],Toast.LENGTH_LONG).show();



                      /* databaseReference_home.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               userlist.clear();
                               for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){

                                   Users users=userSnapshot.getValue(Users.class);
                                   userlist.add(users);

                               }

                               online_userlist adapter= new online_userlist(getActivity(),userlist);
                               list_home.setAdapter(adapter);
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {

                           }
                       });

                       */



                       //startActivity(new Intent(getActivity(), Commuters.class));
                   }
               });




           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });




        return root;
    }
}