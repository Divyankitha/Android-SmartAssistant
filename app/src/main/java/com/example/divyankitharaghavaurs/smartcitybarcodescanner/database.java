package com.example.divyankitharaghavaurs.smartcitybarcodescanner;

/**
 * Created by divyankithaRaghavaUrs on 9/19/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.view.View;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class database extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Feedback.db";

    public database(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table Feedback " + "(Comment text,Rating text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Feedback");
        onCreate(db);
    }

    public boolean insertFeedback (String comment,String rating)
    {

        Log.d("Debug -->", "inside insert feedback of database class");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Comment", comment);
        contentValues.put("Rating", rating);

        db.insert("Feedback", null, contentValues);

        System.out.println("Comment: " +comment);
        System.out.println("Rating: " +rating);
        return true;
    }



    public int numberOfRows()
    {
        int numRows ;
        SQLiteDatabase db = this.getReadableDatabase();

        numRows = (int) DatabaseUtils.queryNumEntries(db, "Feedback");

        return numRows;
    }

}