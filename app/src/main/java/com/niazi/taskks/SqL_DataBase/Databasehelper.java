package com.niazi.taskks.SqL_DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.sql.SQLDataException;
import java.util.ConcurrentModificationException;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "SURNAME";
    private static final String COL_4 = "MARK";


    public Databasehelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT," +
                " SURNAME TEXT , MARKS INTEGER) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String name,String surname,String mark) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, mark);

        long sucess = db.insert(TABLE_NAME, null, contentValues);

        if (sucess == -1) {


            return false;
        } else {

            return true;

        }

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cur  = db.rawQuery("select * from "+TABLE_NAME,null);
        return cur;
    }

    public boolean updatedata(String id,String name,String surname,String mark)
    {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,mark);

        database.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});

        return true;
    }

    public  Integer deletedata(String id)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID = ?",new String[] {id});

    }
}
