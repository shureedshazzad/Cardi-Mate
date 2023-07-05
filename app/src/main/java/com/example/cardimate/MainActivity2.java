package com.example.cardimate;    //sign in page

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private Button sign_up_page_button;
    private Button sign_in_button;
    ProgressBar progressBar1;
    FirebaseAuth firebaseAuth;

    @Override
    public void onStart() {      //if user is already signed in then directly move to activity interface
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
        setContentView(R.layout.activity_main2);
        final EditText email=findViewById(R.id.email);

        final EditText password=findViewById(R.id.password);
        sign_up_page_button=findViewById(R.id.btn_2);
        sign_in_button=findViewById(R.id.btn_1);
        progressBar1=findViewById(R.id.p1);
        firebaseAuth=FirebaseAuth.getInstance();

        sign_up_page_button.setOnClickListener(new View.OnClickListener() {      //new user will go to activity 3 to sign up
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });

        sign_in_button.setOnClickListener(new View.OnClickListener() {       //user will sign in using email and password and go to activity interface
            @Override
            public void onClick(View view) {

                final String emailtext=email.getText().toString();
                final String passwordtext=password.getText().toString();
                if(emailtext.isEmpty() || passwordtext.isEmpty()){
                    Toast.makeText(MainActivity2.this,"Please enter your email and password",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar1.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(emailtext,passwordtext)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar1.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Sign In Successful",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),Interface.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(MainActivity2.this,"Sorry,Wrong Email Or Password",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}