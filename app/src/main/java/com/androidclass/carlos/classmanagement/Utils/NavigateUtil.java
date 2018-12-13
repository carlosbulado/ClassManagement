package com.androidclass.carlos.classmanagement.Utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public final class  NavigateUtil
{
    public static void goTo(AppCompatActivity thisActivity, Class<?> nextActivity)
    {
        Intent mainIntent = new Intent(thisActivity, nextActivity);
        thisActivity.startActivity(mainIntent);
        thisActivity.finish();
    }
}
