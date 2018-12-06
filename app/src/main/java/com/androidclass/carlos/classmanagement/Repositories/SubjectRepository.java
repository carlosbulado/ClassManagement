package com.androidclass.carlos.classmanagement.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.Domain.Subject;

import java.util.ArrayList;
import java.util.HashMap;

public class SubjectRepository extends BaseRepository<Subject>
{
    public static final String TABLE_NAME = "subjects";
    public static final String TABLE_REF_STUDENTS_NAME = "subject_student";
    public static final String TABLE_REF_STUDENTS_SUBJECT_ID = "subject_id";
    public static final String TABLE_REF_STUDENTS_STUDENT_ID = "student_id";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final HashMap<String, String> TABLE_COLUMNS = new HashMap<String, String>() {{
        put("id", "INT");
        put("name", "TEXT");
    }};

    public SubjectRepository (Context context)
    {
        super(context, TABLE_NAME, TABLE_COLUMNS, ID);
    }

    public SubjectRepository (SQLiteDatabase database)
    {
        super(database, TABLE_NAME, TABLE_COLUMNS, ID);
    }

    @Override
    public ArrayList<Subject> getAll ()
    {
        StudentRepository stuRep = new StudentRepository(this.db);
        ArrayList<Subject> subs = super.getAll();
        for (Subject sub : subs)
        {
            ArrayList<Student> stuArr = stuRep.getAllBySubjectId(sub.getId());
            sub.setStudents(stuArr);
        }
        return subs;
    }

    @Override
    public Subject getById (int id)
    {
        StudentRepository stuRep = new StudentRepository(this.db);
        Subject sub = super.getById(id);
        ArrayList<Student> stuArr = stuRep.getAllBySubjectId(sub.getId());
        sub.setStudents(stuArr);
        return sub;
    }

    @Override
    protected ContentValues getAllValues(Subject obj)
    {
        ContentValues values = new ContentValues();

        values.put(NAME, obj.getName());

        return values;
    }

    @Override
    protected Subject loadObject (Cursor linesCursor)
    {
        Subject obj = new Subject();
        obj.setId(linesCursor.getInt(linesCursor.getColumnIndex(ID)));
        obj.setName(linesCursor.getString(linesCursor.getColumnIndex(NAME)));

        return obj;
    }

    public ArrayList<Subject> getAllByUserId(int id)
    {
        ArrayList<Subject> subArr = new ArrayList<>();

        Cursor mCursor = db.query(UserRepository.TABLE_REF_SUBJECTS_NAME, new String [] { UserRepository.TABLE_REF_SUBJECTS_SUBJECT_ID }, UserRepository.TABLE_REF_SUBJECTS_USER_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (mCursor != null && mCursor.getCount() > 0)
        {
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast())
            {
                subArr.add(this.getById(mCursor.getInt(0)));
                mCursor.moveToNext();
            }
        }

        return subArr;
    }
}
