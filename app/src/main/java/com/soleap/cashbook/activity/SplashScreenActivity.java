package com.soleap.cashbook.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soleap.cashbook.Global;
import com.soleap.cashbook.R;
import com.soleap.cashbook.content.AppPrefrences;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView backgroundImage;
    TextView soleapName;
    TextView welcomeText;

    Animation sideAnim, alphaAnim, topAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        backgroundImage = findViewById(R.id.imageView);
        soleapName = findViewById(R.id.textView);
        welcomeText = findViewById(R.id.welcomeText);

        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        backgroundImage.setAnimation(topAnim);
        soleapName.setAnimation(sideAnim);
        welcomeText.setAnimation(alphaAnim);

        Global.context = getApplicationContext();

        new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                  if (!AppPrefrences.isUserLogin(SplashScreenActivity.this)) {
                      Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                      startActivity(intent);
                  }
                  else {
                      Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                      startActivity(intent);
                  }
                  finish();
                  }
              }, 3000);
    }
}