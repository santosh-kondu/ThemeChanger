package com.example.santoshkondu.samplemeterialdesign;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

public class ThemeChanger extends AppCompatActivity {

    Handler taskHandler = new Handler();
    private String APP_NAME;
    Timer mainTimer = new Timer();
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_changer);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(View.GONE);

        mainTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                taskHandler.post(themeChange);
            }
        },1000,1000);
        APP_NAME = getString(R.string.app_name);
    }

    Runnable themeChange = new Runnable() {
        @Override
        public void run() {

            int seconds = (int)((System.currentTimeMillis()/1000) % 60);

            seconds = seconds % 4;
            Log.d(APP_NAME, "Theme changing is is not ? " + seconds);
            if (Build.VERSION.SDK_INT >= 21) {
                switch (seconds) {

                    case 0:

                        getWindow().setNavigationBarColor(Color.BLACK);
                        getWindow().setStatusBarColor(Color.BLACK);
                        toolbar.setBackgroundColor(Color.GREEN);
                        toolbar.setTitleTextColor(Color.WHITE);
                        getWindow().setBackgroundDrawableResource(R.drawable.blue);
                        break;
                    case 1:
                        //setTheme(R.style.AppTheme_AppBarOverlay);
                        getWindow().setNavigationBarColor(Color.RED);
                        getWindow().setStatusBarColor(Color.RED);
                        toolbar.setBackgroundColor(Color.WHITE);
                        toolbar.setTitleTextColor(Color.BLACK);
                        getWindow().setBackgroundDrawableResource(R.drawable.red);
                        break;
                    case 2:
                        //setTheme(R.style.AppTheme_NoActionBar);
                        getWindow().setNavigationBarColor(Color.YELLOW);
                        getWindow().setStatusBarColor(Color.YELLOW);
                        toolbar.setBackgroundColor(Color.BLACK);
                        toolbar.setTitleTextColor(Color.RED);
                        getWindow().setBackgroundDrawableResource(R.drawable.pink);
                        break;
                    case 3:
                        //setTheme(R.style.AppTheme_PopupOverlay);
                        getWindow().setNavigationBarColor(Color.WHITE);
                        getWindow().setStatusBarColor(Color.WHITE);
                        toolbar.setBackgroundColor(Color.RED);
                        toolbar.setTitleTextColor(Color.YELLOW);
                        getWindow().setBackgroundDrawableResource(R.drawable.purple);
                        break;
                    default:
                }
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theme_changer, menu);
        return true;
    }

    @Override
    protected void onStop() {
        mainTimer.cancel();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
