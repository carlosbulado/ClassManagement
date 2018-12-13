package com.androidclass.carlos.classmanagement;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidclass.carlos.classmanagement.Domain.User;
import com.androidclass.carlos.classmanagement.Repositories.UserRepository;
import com.androidclass.carlos.classmanagement.Services.LoginService;
import com.androidclass.carlos.classmanagement.Utils.NavigateUtil;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.loginPage_login)
    EditText loginText;
    @BindView(R.id.loginPage_password)
    EditText passwordText;
    private SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myPref = getSharedPreferences("userDetails", MODE_PRIVATE);

        if(myPref.getInt("UserId", 0) > 0)  NavigateUtil.goTo(this, ListClassesActivity.class);
    }

    public void onLoginButtonClick(View view)
    {
        String login = loginText.getText().toString();
        String pass = passwordText.getText().toString();

        User loggedUser = ServiceUtils.loginService.Login(login, pass);
        if(loggedUser.getId() != 0)
        {
            myPref.edit().putInt("UserId", loggedUser.getId()).commit();
            NavigateUtil.goTo(this, ListClassesActivity.class);
        }
        else
        {
            Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegisterButtonClick(View view)
    {
        NavigateUtil.goTo(this, RegisterActivity.class);
    }
}
