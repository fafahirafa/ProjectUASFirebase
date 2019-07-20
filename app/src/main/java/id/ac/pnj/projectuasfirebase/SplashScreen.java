package id.ac.pnj.projectuasfirebase;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img_logo = (ImageView) findViewById(R.id.img_logo);

        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setFillAfter(true);
        animation.setDuration(2000);
        img_logo.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreen.this, IntroductionActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}


