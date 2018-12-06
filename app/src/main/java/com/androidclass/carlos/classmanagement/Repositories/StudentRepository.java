package com.androidclass.carlos.classmanagement.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidclass.carlos.classmanagement.Domain.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRepository extends BaseRepository<Student>
{
    public static final String TABLE_NAME = "students";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TOTAL_MARKS = "total_marks";
    public static final HashMap<String, String> TABLE_COLUMNS = new HashMap<String, String>() {{
        put("id", "INT");
        put("name", "TEXT");
        put("total_marks", "TEXT");
    }};

    public StudentRepository (Context context)
    {
        super(context, TABLE_NAME, TABLE_COLUMNS, ID);
    }

    public StudentRepository (SQLiteDatabase database)
    {
        super(database, TABLE_NAME, TABLE_COLUMNS, ID);
    }

    @Override
    protected ContentValues getAllValues(Student obj)
    {
        ContentValues values = new ContentValues();

        values.put(NAME, obj.getName());
        values.put(TOTAL_MARKS, obj.getTotalMarks());

        return values;
    }

    @Override
    protected Student loadObject (Cursor linesCursor)
    {
        Student obj = new Student();
        obj.setId(linesCursor.getInt(linesCursor.getColumnIndex(ID)));
        obj.setName(linesCursor.getString(linesCursor.getColumnIndex(NAME)));
        obj.setTotalMarks(linesCursor.getInt(linesCursor.getColumnIndex(TOTAL_MARKS)));

        return obj;
    }

    public ArrayList<Student> getAllBySubjectId(int id)
    {
        ArrayList<Student> stuArr = new ArrayList<>();

        Cursor mCursor = db.query(SubjectRepository.TABLE_REF_STUDENTS_NAME, new String [] { SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID }, SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (mCursor != null && mCursor.getCount() > 0)
        {
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast())
            {
                stuArr.add(this.getById(mCursor.getInt(0)));
                mCursor.moveToNext();
            }
        }

        return stuArr;
    }
}
