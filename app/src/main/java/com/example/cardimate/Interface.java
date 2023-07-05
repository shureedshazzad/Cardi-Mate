package com.example.cardimate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * This is the  User Interface that contains Create and Show Record and also a Logout button
 */
public class Interface extends AppCompatActivity {

    private Button create_record_btn,show_record_btn,sign_out_button;

    /**
     *This is oncreate method
     * @param savedInstanceState Bundle of arguments
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        create_record_btn=findViewById(R.id.record_create);
        show_record_btn=findViewById(R.id.record_show);
        sign_out_button=findViewById(R.id.sign_out);


        /**
         * By Clicking on this button , user moves to mainactivity4, where he can input new data/measurement
         */
        create_record_btn.setOnClickListener(new View.OnClickListener() {//this buuton will move to mainactivity 4
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Interface.this,MainActivity4.class);
                startActivity(intent);
                //finish();;
            }
        });

        /**
         * By Clicking on this button , we move to mainactivity5, where user fetches the existing activites
         * for each individual users and show them in a recyler view card
         */

        show_record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Interface.this,MainActivity5.class);
                startActivity(intent);
                //finish();
            }
        });

        /**
         * By Clicking on this button , we move to mainactivity2, the user signs out from the app
         */
        sign_out_button.setOnClickListener(new View.OnClickListener() {//this button will sign out and move to mainactivity 2
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Signing Out",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });

    }
}