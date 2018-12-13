package com.androidclass.carlos.classmanagement.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidclass.carlos.classmanagement.Database.DBHelper;
import com.androidclass.carlos.classmanagement.Domain.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseRepository<T extends Entity>
{
    private String TableName;
    private String ID;
    private HashMap<String, String> TableColumns;
    protected DBHelper dbHelper;
    protected SQLiteDatabase db;

    public BaseRepository (Context context, String tableName, HashMap<String, String> tableColumns, String id)
    {
        dbHelper = new DBHelper(context);
        TableName = tableName;
        TableColumns = tableColumns;
        ID = id;
        db = dbHelper.getWritableDatabase();
    }

    public BaseRepository (SQLiteDatabase database, String tableName, HashMap<String, String> tableColumns, String id)
    {
        TableName = tableName;
        TableColumns = tableColumns;
        ID = id;
        db = database;
    }

    public String getTableName ()
    {
        return TableName;
    }

    public String getID ()
    {
        return ID;
    }

    public HashMap<String, String> getTableColumns ()
    {
        return TableColumns;
    }

    public T insert(T obj)
    {
        db.insert(this.getTableName(), null, this.getAllValues(obj));
        return this.getSavedObject(obj);
    }

    public T update(T obj)
    {
        db.update(this.getTableName(), this.getAllValues(obj), this.getID() + " = ? ", new String[] { String.valueOf(obj.getId()) });
        return this.getSavedObject(obj);
    }

    public void delete(T obj)
    {
        db.delete(this.getTableName(), this.getID() + " = ? ", new String[] { String.valueOf(obj.getId()) });
    }

    public ArrayList<T> getAll()
    {
        Cursor mCursor = db.rawQuery("SELECT * FROM " + this.getTableName(), null);

        ArrayList<T> objs = new ArrayList<>();
        if (mCursor != null && mCursor.getCount() > 0)
        {
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast())
            {
                objs.add(this.loadObject(mCursor));
                mCursor.moveToNext();
            }
        }

        return objs;
    }

    public T getById (int id)
    {
        Cursor mCursor = db.query(this.getTableName(), null, this.getID() + " = ? ", new String[] { String.valueOf(id) }, null, null, null, null);

        if (mCursor != null && mCursor.getCount() > 0)
        {
            mCursor.moveToFirst();
            return this.loadObject(mCursor);
        }

        return null;
    }

    protected void beginTransaction()
    {
        if (!this.db.inTransaction())
        {
            this.db.beginTransaction();
        }
    }

    protected void endTransaction()
    {
        if (this.db.inTransaction())
        {
            this.db.endTransaction();
        }
    }

    protected void close()
    {
        this.endTransaction();
        this.db.close();
    }

    protected abstract ContentValues getAllValues(T obj);
    protected abstract T loadObject (Cursor linesCursor);

    private T getSavedObject(T obj)
    {
        if (obj.getId() > 0)
        {
            return this.getById(obj.getId());
        }
        else
        {
            ArrayList<T> arr = this.getAll();
            return arr.get(arr.size() - 1);
        }
    }
}
