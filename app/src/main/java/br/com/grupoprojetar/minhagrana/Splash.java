package br.com.grupoprojetar.minhagrana;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    private int SPLASH_TIME=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(SPLASH_TIME);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                Intent mdTela = new Intent(getApplicationContext(), Login.class);
                startActivity(mdTela);
            }

        };

        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
