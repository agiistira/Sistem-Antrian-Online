package com.example.ngantritest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView image, logo;
    TextView text, text_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Inisiasi View
        android.view.View decorView = getWindow().getDecorView();

//        Animasi
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.image);
        logo = findViewById(R.id.logo);
        text = findViewById(R.id.text);
        text_logo= findViewById(R.id.text_logo);

        text.setAnimation(topAnim);
        image.setAnimation(bottomAnim);
        logo.setAnimation(bottomAnim);
        text_logo.setAnimation(bottomAnim);


//        Hide status bar
        decorView.setSystemUiVisibility(android.view.View.SYSTEM_UI_FLAG_FULLSCREEN);
//      Hide Action Bar
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
//
//        Timer
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Login.class));
                finish();
            }
        }, 3000);
    }
}