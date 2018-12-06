package com.androidclass.carlos.classmanagement.Domain;

import java.util.ArrayList;

public class User extends Entity
{
    private String Name;
    private String Login;
    private String Password;
    private ArrayList<Subject> Subjects;

    public String getName ()
    {
        return Name;
    }

    public void setName (String name)
    {
        Name = name;
    }

    public String getLogin ()
    {
        return Login;
    }

    public void setLogin (String login)
    {
        Login = login;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String password)
    {
        Password = password;
    }

    public ArrayList<Subject> getSubjects ()
    {
        return Subjects;
    }

    public void setSubjects (ArrayList<Subject> subjects)
    {
        Subjects = subjects;
    }
}
