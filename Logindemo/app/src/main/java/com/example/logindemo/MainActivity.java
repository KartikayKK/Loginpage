package com.example.logindemo;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText mail,pass,name;
    TextView login;
    Button signup;
    Button button2;
    int k=0;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.pass);
        name=findViewById(R.id.name);
        login=findViewById(R.id.link_login);
        signup =findViewById(R.id.btn_signup);
        name=findViewById(R.id.name);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (k==0){
                button2.setBackground(null);
                button2.setText("Rate!!");
                button2.setTextColor(Color.parseColor("#0000ff"));
                k+=1;
                }
                else{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.kkapp.jame")));
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivity.this,"Feilds are empty",Toast.LENGTH_LONG);
                }else if(!(email.isEmpty() && spass.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(email,spass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Unsucceful,try again",Toast.LENGTH_LONG);
                            }else{
                                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this,"ERRRRRRRRRRRRRROR",Toast.LENGTH_LONG);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });
    }

}
