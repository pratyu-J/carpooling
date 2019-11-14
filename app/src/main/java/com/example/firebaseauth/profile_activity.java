package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_activity extends AppCompatActivity {

    EditText name ,rollno;
    TextView text;
    Button save;
    DatabaseReference databaseReference;
    Users users;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        name=findViewById(R.id.pa_name);
        rollno=findViewById(R.id.pa_roll);
        save=findViewById(R.id.save_button);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        users=new Users();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.setUrollno(rollno.getText().toString().trim());
                users.setUname(name.getText().toString().trim());

                String id=databaseReference.push().getKey();

                databaseReference.child(id).setValue(users);
                Toast.makeText(profile_activity.this,"insertion successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(profile_activity.this,NavActivity.class));
                finish();
            }
        });


    }
}
