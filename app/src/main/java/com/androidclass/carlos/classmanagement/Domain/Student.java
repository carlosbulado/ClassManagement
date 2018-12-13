package com.androidclass.carlos.classmanagement.Domain;

import android.widget.EditText;

public class Student extends Entity
{
    private String Name;
    private int TotalMarks;

    private EditText txtName;
    private EditText txtMarks;

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

    public EditText getTxtName ()
    {
        return txtName;
    }

    public void setTxtName (EditText txtName)
    {
        this.txtName = txtName;
    }

    public EditText getTxtMarks ()
    {
        return txtMarks;
    }

    public void setTxtMarks (EditText txtMarks)
    {
        this.txtMarks = txtMarks;
    }
}
