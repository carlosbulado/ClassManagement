package com.androidclass.carlos.classmanagement.Services;

import android.content.Context;

import com.androidclass.carlos.classmanagement.Domain.Subject;
import com.androidclass.carlos.classmanagement.Repositories.SubjectRepository;

import java.util.ArrayList;

public class SubjectService extends BaseService<Subject>
{
    public SubjectService (Context context)
    {
        super(context, new SubjectRepository(context));
    }

    public ArrayList<Subject> getAllByUserId(int id)
    {
        SubjectRepository sr = new SubjectRepository(this.getContext());
        return sr.getAllByUserId(id);
    }
}
