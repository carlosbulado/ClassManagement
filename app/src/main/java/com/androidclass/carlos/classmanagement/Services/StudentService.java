package com.androidclass.carlos.classmanagement.Services;

import android.content.Context;

import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.Repositories.StudentRepository;

import java.util.ArrayList;

public class StudentService extends BaseService<Student>
{
    public StudentService (Context context)
    {
        super(context, new StudentRepository(context));
    }

    public ArrayList<Student> getAllBySubjectId(int id)
    {
        StudentRepository sr = new StudentRepository(this.getContext());
        return sr.getAllBySubjectId(id);
    }
}
