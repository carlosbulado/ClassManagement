package com.androidclass.carlos.classmanagement.Domain;

public abstract class Entity
{
    private int Id;

    public int getId ()
    {
        return Id;
    }

    public void setId (int id)
    {
        Id = id;
    }
}
