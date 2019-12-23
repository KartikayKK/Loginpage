package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText mail,pass,name;
    TextView signup;
    Button login;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.lmail);
        pass=findViewById(R.id.lpas);
        name=findViewById(R.id.name);
        signup=findViewById(R.id.link_signup);
        login =findViewById(R.id.btn_login);

        f=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(Login.this,"Logged in yayy",Toast.LENGTH_LONG);
                    startActivity(new Intent(Login.this,Main2Activity.class));
                }else{
                    Toast.makeText(Login.this,"Nopes",Toast.LENGTH_LONG);

                }
            }
        };


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mail.getText().toString();
                String spass=pass.getText().toString();
                if(email.isEmpty()){
                    mail.setError("Enter Email_Id");
                    mail.requestFocus();
                }else if(spass.isEmpty()){
                    pass.setError("Enter password");
                    pass.requestFocus();
                } else if(email.isEmpty() && spass.isEmpty()){
                    Toast.makeText(Login.this,"Feilds are empty",Toast.LENGTH_LONG);
                }else if(!(email.isEmpty() && spass.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email,spass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Login.this,"Unsucceful,try again",Toast.LENGTH_LONG);
                            }else{
                                startActivity(new Intent(Login.this,Main2Activity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(Login.this,"ERRRRRRRRRRRRRROR",Toast.LENGTH_LONG);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(f);
    }
}
