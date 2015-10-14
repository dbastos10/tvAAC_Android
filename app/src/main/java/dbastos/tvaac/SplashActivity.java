package dbastos.tvaac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dbast on 01/09/2015.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        Thread mythread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent start_MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(start_MainActivity);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
}
