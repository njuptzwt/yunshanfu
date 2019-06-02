package com.example.yunshanfu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.example.yunshanfu.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 欢迎界面
 */

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Intent mainPageIntent=new Intent(MainActivity.this, MainPage1.class);
                startActivity(mainPageIntent);
                MainActivity.this.finish();
            }
        };
        timer.schedule(timerTask,2000);
    }
}
