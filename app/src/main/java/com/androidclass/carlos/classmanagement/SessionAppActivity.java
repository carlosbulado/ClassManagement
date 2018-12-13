package com.androidclass.carlos.classmanagement;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidclass.carlos.classmanagement.Domain.User;
import com.androidclass.carlos.classmanagement.Utils.NavigateUtil;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

public class SessionAppActivity extends AppCompatActivity
{
    public User loggedUser = new User();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_app);

        this.checkUserSession();
    }

    public void checkUserSession()
    {
        SharedPreferences myPref = getSharedPreferences("userDetails", MODE_PRIVATE);

        int userId = myPref.getInt("UserId", 0);
        if(userId > 0) this.loggedUser = ServiceUtils.userService.getRepository().getById(userId);
        else NavigateUtil.goTo(this, MainActivity.class);
    }

    public User getLoggedUser()
    {
        return this.loggedUser;
    }
}
