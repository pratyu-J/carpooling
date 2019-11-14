package com.example.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    TextView textView,ForgotPass_tv;
    EditText UserEmail,UserPass;
    Button loginButton;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    CheckBox main_cb;


    private boolean network(){

        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activenetworkinfo=connectivityManager.getActiveNetworkInfo();
        return activenetworkinfo !=null && activenetworkinfo.isConnected();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(MainActivity.this,NavActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserEmail=findViewById(R.id.user_email);
        UserPass=findViewById(R.id.password);
        loginButton=findViewById(R.id.login);
        textView=findViewById(R.id.textbox);
        progressBar=findViewById(R.id.prbar);
        mAuth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);
        ForgotPass_tv=findViewById(R.id.forgot_id);
        main_cb=findViewById(R.id.main_cb_id);

        if(!network()){
            Log.i("TRUE","Not connected to internet");
            Toast.makeText(MainActivity.this,"Not connected to internet",Toast.LENGTH_LONG).show();
        }

        ForgotPass_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ResetPassword.class));
                finish();
            }
        });

        main_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    UserPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    UserPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this,Register.class));

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {



                String User_Email=UserEmail.getText().toString().trim();
                String User_Pass=UserPass.getText().toString().trim();
                if(User_Email.isEmpty()){
                    UserEmail.setError("Please Enter Email!!");
                    UserEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(User_Email).matches()){
                    UserEmail.setError("Please Enter A valid Email!!");
                    UserEmail.requestFocus();
                    return;
                }
                if(User_Pass.isEmpty()){
                    UserPass.setError("Please Set Password!!");
                    UserPass.requestFocus();
                    return;
                }
                if(User_Pass.length()<6){
                    UserPass.setError("Password should be 6 character long ");
                    UserPass.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(User_Email,User_Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            progressBar.setVisibility(view.GONE);
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,NavActivity.class));
                        }
                        else {
                            progressBar.setVisibility(view.GONE);
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });
    }
}
