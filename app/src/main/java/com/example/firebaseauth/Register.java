package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity {
    EditText Email,Password;
    Button registerb;
    TextView gologin;
    ProgressBar rgpbar;
    CheckBox cb_rg;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.rpassword);
        registerb=findViewById(R.id.register_button);
        gologin=findViewById(R.id.gotologin);
        rgpbar=findViewById(R.id.reg_pbar);
        cb_rg=findViewById(R.id.cb_rg_id);

        cb_rg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        rgpbar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        //getting userinfo

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,MainActivity.class));
                finish();
            }
        });

        registerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=Email.getText().toString().trim();
               
                String userpass=Password.getText().toString().trim();

                if(userEmail.isEmpty()){
                    Email.setError("Please Enter Email!!");
                    Email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    Email.setError("Please Enter A valid Email!!");
                    Email.requestFocus();
                    return;
                }
                if(userpass.isEmpty()){
                    Password.setError("Please Set Password!!");
                    Password.requestFocus();
                    return;
                }
                if(userpass.length()<6){
                    Password.setError("Password should be 6 character long ");
                    Password.requestFocus();
                    return;
                }
                rgpbar.setVisibility(View.VISIBLE);


                //registration
             mAuth.createUserWithEmailAndPassword(userEmail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        finish();
                        rgpbar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Registration Successful",Toast.LENGTH_LONG).show();

                        startActivity(new Intent(Register.this,profile_activity.class));
                    }
                    else{
                        rgpbar.setVisibility(View.GONE);
                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(),"Email already registered",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                 }
             });



            }
        });






    }


}
