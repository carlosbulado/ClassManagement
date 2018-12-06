package com.androidclass.carlos.classmanagement.Services;

import android.content.Context;

import com.androidclass.carlos.classmanagement.Domain.User;
import com.androidclass.carlos.classmanagement.Repositories.UserRepository;

public class UserService extends BaseService<User>
{
    public UserService (Context context)
    {
        super(context, new UserRepository(context));
    }
}
