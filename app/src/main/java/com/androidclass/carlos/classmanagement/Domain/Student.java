package com.androidclass.carlos.classmanagement.Domain;

public class Student extends Entity
{
    private String Name;
    private int TotalMarks;

    public String getName ()
    {
        return Name;
    }

    public void setName (String name)
    {
        Name = name;
    }

    public int getTotalMarks ()
    {
        return TotalMarks;
    }

    public void setTotalMarks (int totalMarks)
    {
        TotalMarks = totalMarks;
    }
}
