package com.example.cardimate; //signup page

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity3 extends AppCompatActivity {

    private Button sign_in_page_button,sign_up_button;
    FirebaseAuth firebaseAuth;

    ProgressBar progressBar2;

    public static String e;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent=new Intent(getApplicationContext(),Interface.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final EditText email_input,password_input;
        firebaseAuth=FirebaseAuth.getInstance();
        sign_in_page_button=findViewById(R.id.btn_4);
        email_input=findViewById(R.id.input_email);
        password_input=findViewById(R.id.input_password);
        sign_up_button=findViewById(R.id.btn_3);
        progressBar2=findViewById(R.id.p2);

        sign_up_button.setOnClickListener(new View.OnClickListener() {    //new user will sign up using email and password
            @Override
            public void onClick(View view) {
                e=email_input.getText().toString();
                final String emailtext=email_input.getText().toString();
                final String passwordtext=password_input.getText().toString();
                if(emailtext.isEmpty() || passwordtext.isEmpty()){
                    Toast.makeText(MainActivity3.this,"Please enter your email and a password",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar2.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(emailtext,passwordtext)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar2.setVisibility(View.GONE);
                                    if(task.isSuccessful()){

                                        Toast.makeText(MainActivity3.this,"Account Created",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(MainActivity3.this,MainActivity2.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(MainActivity3.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        sign_in_page_button.setOnClickListener(new View.OnClickListener() {// if already account exits the go to activity 2 to sign in
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });


    }
}