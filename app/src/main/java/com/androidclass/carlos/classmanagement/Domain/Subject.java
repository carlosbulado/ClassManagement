package com.androidclass.carlos.classmanagement.Domain;

import java.util.ArrayList;

public class Subject extends Entity
{
    private String Name;
    private ArrayList<Student> Students;
    private User Professor;

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
        if (Students == null) return new ArrayList<>();
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

        Double dob = Double.parseDouble(String.valueOf(stMarks));

        return this.getStudents().size() > 0 ? dob / this.getStudents().size() : 0;
    }

    public User getProfessor ()
    {
        return Professor;
    }

    public void setProfessor (User professor)
    {
        Professor = professor;
    }
}
