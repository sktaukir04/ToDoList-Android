package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        imageView=(ImageView)findViewById(R.id.splashscreenimg);
        textView=(TextView)findViewById(R.id.splash_textview);
        Animation slideAnimation= AnimationUtils.loadAnimation(this,R.anim.slide);
        imageView.startAnimation(slideAnimation);
        textView.startAnimation(slideAnimation);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,CountryList.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}