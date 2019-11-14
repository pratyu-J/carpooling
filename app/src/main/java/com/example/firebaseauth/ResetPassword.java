package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

public class ResetPassword extends AppCompatActivity {

    EditText resetEmail;
    Button Linkbutton;
    FirebaseAuth auth;
    ProgressBar rp_progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        resetEmail=findViewById(R.id.reset_pass_id);
        Linkbutton=findViewById(R.id.reset_button_id);
        auth=FirebaseAuth.getInstance();
        rp_progressBar=findViewById(R.id.rp_pbar);
        rp_progressBar.setVisibility(View.GONE);




        Linkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email=resetEmail.getText().toString().trim();
                 if(email.isEmpty()){
                     resetEmail.setError("Please Enter Email!!");
                     resetEmail.requestFocus();
                     return;
                 }
                 else {

                     rp_progressBar.setVisibility(View.VISIBLE);
                     auth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                         @Override
                         public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                             boolean check=task.getResult().getProviders().isEmpty();
                             if (check){
                                 Toast.makeText(getApplicationContext()," Email Not Registered!!",Toast.LENGTH_LONG).show();
                                 rp_progressBar.setVisibility(View.GONE);
                             }

                             else{

                                 auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                     @Override
                                     public void onComplete(@NonNull Task<Void> task) {
                                         if(task.isSuccessful()){
                                             Toast.makeText(getApplicationContext(),"Check Email!!",Toast.LENGTH_LONG).show();
                                             rp_progressBar.setVisibility(View.GONE);
                                             finish();
                                         }
                                     }
                                 });
                             }



                         }
                     });

                 }


            }
        });
    }



}
