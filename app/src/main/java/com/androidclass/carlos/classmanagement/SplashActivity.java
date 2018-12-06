package com.androidclass.carlos.classmanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidclass.carlos.classmanagement.Database.DBHelper;
import com.androidclass.carlos.classmanagement.Services.LoginService;
import com.androidclass.carlos.classmanagement.Services.SubjectService;
import com.androidclass.carlos.classmanagement.Services.UserService;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        scheduleSplashScreen();
    }

    private void scheduleSplashScreen()
    {
        long splashScreenDuration = getSplashScreenDuration();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run ()
            {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 0, 0);
                ServiceUtils.loginService = new LoginService(getApplicationContext());
                ServiceUtils.userService = new UserService(getApplicationContext());
                ServiceUtils.subjectService = new SubjectService(getApplicationContext());
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, splashScreenDuration);
    }

    private long getSplashScreenDuration() { return 2000L; }
}
