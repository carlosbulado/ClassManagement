package com.androidclass.carlos.classmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.androidclass.carlos.classmanagement.Domain.User;
import com.androidclass.carlos.classmanagement.Utils.NavigateUtil;
import com.androidclass.carlos.classmanagement.Utils.ServiceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity
{
    @BindView(R.id.registerPage_name)
    EditText name;
    @BindView(R.id.registerPage_login)
    EditText login;
    @BindView(R.id.registerPage_password)
    EditText password;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public void onRegisterButtonClick(View view)
    {
        User user = new User();
        user.setName(name.getText().toString());
        user.setLogin(login.getText().toString());
        user.setPassword(password.getText().toString());

        ServiceUtils.userService.save(user);

        NavigateUtil.goTo(this, MainActivity.class);
    }
}
