package com.androidclass.carlos.classmanagement.Domain;

import java.util.ArrayList;

public class Subject extends Entity
{
    private String Name;
    private ArrayList<Student> Students;

    public String getName ()
    {
        return Name;
    }

    public void setName (String name)
    {
        this.Name = name;
    }

    public ArrayList<Student> getStudents ()
    {
        return Students;
    }

    public void setStudents (ArrayList<Student> students)
    {
        Students = students;
    }

    public int getStudentsQuantity()
    {
        return this.getStudents().size();
    }

    public double getAvgStudentsMarksPercent()
    {
        int stMarks = 0;

        for (Student st: this.getStudents())
        {
            stMarks += st.getTotalMarks();
        }

        return stMarks / (this.getStudents().size() * 100);
    }
}
