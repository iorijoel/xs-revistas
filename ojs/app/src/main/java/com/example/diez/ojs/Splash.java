package com.example.diez.ojs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        imageView.startAnimation(animation);

        ImageView imageView2 = findViewById(R.id.imagelogocarrera);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        imageView2.startAnimation(animation2);

        TextView universidad = findViewById(R.id.textViewnombreu);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        universidad.startAnimation(animation3);

        TextView appnombre = findViewById(R.id.textViewnombreApp);
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.appname);
        appnombre.startAnimation(animation4);

        Thread timer = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.start();
    }
}
