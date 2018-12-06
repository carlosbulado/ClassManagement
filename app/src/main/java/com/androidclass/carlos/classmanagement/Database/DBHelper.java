package com.androidclass.carlos.classmanagement.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidclass.carlos.classmanagement.Domain.Student;
import com.androidclass.carlos.classmanagement.Repositories.StudentRepository;
import com.androidclass.carlos.classmanagement.Repositories.SubjectRepository;
import com.androidclass.carlos.classmanagement.Repositories.UserRepository;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    public DBHelper (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase)
    {
        try
        {
            String userTable = " CREATE TABLE " + UserRepository.TABLE_NAME
                    + " ( " + UserRepository.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + UserRepository.NAME + " TEXT, "
                    + UserRepository.LOGIN + " TEXT, "
                    + UserRepository.PASSWORD + " TEXT) ";

            sqLiteDatabase.execSQL(userTable);
        }
        catch (Exception ex) { }
        try
        {
            String insert_admin = "INSERT INTO " + UserRepository.TABLE_NAME
                    + " ( " + UserRepository.NAME + ", "
                    + UserRepository.LOGIN + ", "
                    + UserRepository.PASSWORD + ") "
                    + " VALUES ('Administrator', 'admin', '123')";

            sqLiteDatabase.execSQL(insert_admin);
        }
        catch (Exception ex) { }
        try
        {
            String subjectTable = " CREATE TABLE " + SubjectRepository.TABLE_NAME
                    + " ( " + SubjectRepository.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + SubjectRepository.NAME + " TEXT) ";

            sqLiteDatabase.execSQL(subjectTable);
        }
        catch (Exception ex) { }
        try
        {
            String studentTable = " CREATE TABLE " + StudentRepository.TABLE_NAME
                    + " ( " + StudentRepository.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + StudentRepository.NAME + " TEXT,"
                    + StudentRepository.TOTAL_MARKS + " INTEGER) ";

            sqLiteDatabase.execSQL(studentTable);
        }
        catch (Exception ex) { }
        try
        {
            String subjectStudentTable = " CREATE TABLE " + SubjectRepository.TABLE_REF_STUDENTS_NAME + " ( " + SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + " INTEGER, " + SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID + " INTEGER ) ";

            sqLiteDatabase.execSQL(subjectStudentTable);
        }
        catch (Exception ex) { }
        try
        {
            String userSubjectTable = " CREATE TABLE " + UserRepository.TABLE_REF_SUBJECTS_NAME + " ( " + UserRepository.TABLE_REF_SUBJECTS_USER_ID + " INTEGER, " + UserRepository.TABLE_REF_SUBJECTS_SUBJECT_ID + " INTEGER ) ";

            sqLiteDatabase.execSQL(userSubjectTable);
        }
        catch (Exception ex) { }

        this.insertSomeStudents(sqLiteDatabase);
        this.insertSomeSubjects(sqLiteDatabase);

        String insert_subject_student = "INSERT INTO " + UserRepository.TABLE_REF_SUBJECTS_NAME
                + " ( " + UserRepository.TABLE_REF_SUBJECTS_USER_ID + ", "
                + UserRepository.TABLE_REF_SUBJECTS_SUBJECT_ID + ") "
                + " VALUES ('1', '1')";

        sqLiteDatabase.execSQL(insert_subject_student);

        insert_subject_student = "INSERT INTO " + UserRepository.TABLE_REF_SUBJECTS_NAME
                + " ( " + UserRepository.TABLE_REF_SUBJECTS_USER_ID + ", "
                + UserRepository.TABLE_REF_SUBJECTS_SUBJECT_ID + ") "
                + " VALUES ('1', '2')";

        sqLiteDatabase.execSQL(insert_subject_student);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        try { sqLiteDatabase.execSQL(" DROP TABLE " + UserRepository.TABLE_NAME); }
        catch (Exception ex) { }
        try { sqLiteDatabase.execSQL(" DROP TABLE " + UserRepository.TABLE_REF_SUBJECTS_NAME); }
        catch (Exception ex) { }
        try { sqLiteDatabase.execSQL(" DROP TABLE " + SubjectRepository.TABLE_NAME); }
        catch (Exception ex) { }
        try { sqLiteDatabase.execSQL(" DROP TABLE " + SubjectRepository.TABLE_REF_STUDENTS_NAME); }
        catch (Exception ex) { }
        try { sqLiteDatabase.execSQL(" DROP TABLE " + StudentRepository.TABLE_NAME); }
        catch (Exception ex) { }
        onCreate(sqLiteDatabase);
    }

    public void insertSomeStudents(SQLiteDatabase sqLiteDatabase)
    {
        String insert_student = "INSERT INTO " + StudentRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ", "
                + StudentRepository.TOTAL_MARKS + ") "
                + " VALUES ('Student 01', '90')";

        sqLiteDatabase.execSQL(insert_student);

        insert_student = "INSERT INTO " + StudentRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ", "
                + StudentRepository.TOTAL_MARKS + ") "
                + " VALUES ('Student 02', '60')";

        sqLiteDatabase.execSQL(insert_student);

        insert_student = "INSERT INTO " + StudentRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ", "
                + StudentRepository.TOTAL_MARKS + ") "
                + " VALUES ('Student 03', '40')";

        sqLiteDatabase.execSQL(insert_student);

        insert_student = "INSERT INTO " + StudentRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ", "
                + StudentRepository.TOTAL_MARKS + ") "
                + " VALUES ('Student 04', '100')";

        sqLiteDatabase.execSQL(insert_student);

        insert_student = "INSERT INTO " + StudentRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ", "
                + StudentRepository.TOTAL_MARKS + ") "
                + " VALUES ('Student 05', '48')";

        sqLiteDatabase.execSQL(insert_student);
    }

    public void insertSomeSubjects(SQLiteDatabase sqLiteDatabase)
    {
        String insert_subject = "INSERT INTO " + SubjectRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ") "
                + " VALUES ('Subject 01')";

        sqLiteDatabase.execSQL(insert_subject);


        insert_subject = "INSERT INTO " + SubjectRepository.TABLE_NAME
                + " ( " + StudentRepository.NAME + ") "
                + " VALUES ('Subject 02')";

        sqLiteDatabase.execSQL(insert_subject);

        String insert_subject_student = "INSERT INTO " + SubjectRepository.TABLE_REF_STUDENTS_NAME
                + " ( " + SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + ", "
                + SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID + ") "
                + " VALUES ('1', '1')";

        sqLiteDatabase.execSQL(insert_subject_student);

        insert_subject_student = "INSERT INTO " + SubjectRepository.TABLE_REF_STUDENTS_NAME
                + " ( " + SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + ", "
                + SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID + ") "
                + " VALUES ('1', '2')";

        sqLiteDatabase.execSQL(insert_subject_student);

        insert_subject_student = "INSERT INTO " + SubjectRepository.TABLE_REF_STUDENTS_NAME
                + " ( " + SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + ", "
                + SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID + ") "
                + " VALUES ('2', '3')";

        sqLiteDatabase.execSQL(insert_subject_student);

        insert_subject_student = "INSERT INTO " + SubjectRepository.TABLE_REF_STUDENTS_NAME
                + " ( " + SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + ", "
                + SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID + ") "
                + " VALUES ('2', '4')";

        sqLiteDatabase.execSQL(insert_subject_student);

        insert_subject_student = "INSERT INTO " + SubjectRepository.TABLE_REF_STUDENTS_NAME
                + " ( " + SubjectRepository.TABLE_REF_STUDENTS_SUBJECT_ID + ", "
                + SubjectRepository.TABLE_REF_STUDENTS_STUDENT_ID + ") "
                + " VALUES ('2', '5')";

        sqLiteDatabase.execSQL(insert_subject_student);
    }
}
