package com.example.cardimate;//introduction

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is the Splashscreen
 */
public class MainActivity extends AppCompatActivity {

    private static int splash_screen=5000;
    Animation top_animation;
    ImageView imageView;
    TextView textView;

    /**
     *This is oncreate method
     * @param savedInstanceState Bundle of arguments
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        top_animation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        imageView.setAnimation(top_animation);
        textView.setAnimation(top_animation);


        /**
         * This method loads a splash screen and then heads to the login/registration i.e MainActivity2
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                finish();
            }
        },splash_screen);
    }
}