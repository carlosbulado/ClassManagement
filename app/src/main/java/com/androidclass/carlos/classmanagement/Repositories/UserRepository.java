package com.androidclass.carlos.classmanagement.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidclass.carlos.classmanagement.Database.DBHelper;
import com.androidclass.carlos.classmanagement.Domain.Subject;
import com.androidclass.carlos.classmanagement.Domain.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class UserRepository extends BaseRepository<User>
{
    public static final String TABLE_NAME = "users";
    public static final String TABLE_REF_SUBJECTS_NAME = "user_subject";
    public static final String TABLE_REF_SUBJECTS_USER_ID = "user_id";
    public static final String TABLE_REF_SUBJECTS_SUBJECT_ID = "subject_id";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final HashMap<String, String> TABLE_COLUMNS = new HashMap<String, String>() {{
        put("id", "INT");
        put("name", "TEXT");
        put("login", "TEXT");
        put("password", "TEXT");
    }};

    public UserRepository (Context context)
    {
        super(context, TABLE_NAME, TABLE_COLUMNS, ID);
    }

    public UserRepository (SQLiteDatabase database)
    {
        super(database, TABLE_NAME, TABLE_COLUMNS, ID);
    }

    @Override
    public ArrayList<User> getAll ()
    {
        SubjectRepository subRep = new SubjectRepository(this.db);
        ArrayList<User> users = super.getAll();
        for (User user : users)
        {
            ArrayList<Subject> stuArr = subRep.getAllByUserId(user.getId());
            user.setSubjects(stuArr);
        }
        return users;
    }

    @Override
    public User getById (int id)
    {
        SubjectRepository subRep = new SubjectRepository(this.db);
        User user = super.getById(id);
        ArrayList<Subject> stuArr = subRep.getAllByUserId(user.getId());
        user.setSubjects(stuArr);
        return user;
    }

    @Override
    protected ContentValues getAllValues(User user)
    {
        ContentValues values = new ContentValues();

        values.put(NAME, user.getName());
        values.put(LOGIN, user.getLogin());
        values.put(PASSWORD, user.getPassword());

        return values;
    }

    @Override
    protected User loadObject (Cursor linesCursor)
    {
        User user = new User();
        user.setId(linesCursor.getInt(linesCursor.getColumnIndex(ID)));
        user.setName(linesCursor.getString(linesCursor.getColumnIndex(NAME)));
        user.setLogin(linesCursor.getString(linesCursor.getColumnIndex(LOGIN)));
        user.setPassword(linesCursor.getString(linesCursor.getColumnIndex(PASSWORD)));

        return user;
    }
}
