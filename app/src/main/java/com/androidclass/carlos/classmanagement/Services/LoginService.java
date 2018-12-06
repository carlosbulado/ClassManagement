package com.androidclass.carlos.classmanagement.Services;

import android.content.Context;

import com.androidclass.carlos.classmanagement.Domain.User;
import com.androidclass.carlos.classmanagement.Repositories.UserRepository;

import java.util.ArrayList;

public class LoginService extends BaseService<User>
{
    public LoginService (Context context)
    {
        super(context, new UserRepository(context));
    }

    public User Login (String login, String password)
    {
        UserRepository ur = new UserRepository(this.getContext());

        /*
        if (login.equals("admin") && password.equals("123"))
        {
            User user = new User();
            user.setId(1);
            return user;
        }
        */

        ArrayList<User> users = ur.getAll();
        for (User u : users)
            if (login.equals(u.getLogin()) && password.equals(u.getPassword()))
                return u;

        return new User();
    }
}
