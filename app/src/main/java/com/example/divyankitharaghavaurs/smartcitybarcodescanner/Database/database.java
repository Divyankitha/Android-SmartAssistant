package com.example.divyankitharaghavaurs.smartcitybarcodescanner.Database;

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
    private static final int DATABASE_VERSION = 1;


    public database(Context context)
    {
        super(context, DATABASE_NAME , null, 2);
    } //Creating database

    @Override
    public void onCreate(SQLiteDatabase db) // Creating table
    {
        //db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("create table Feedback " + "(Comment text,Rating text)");
        db.execSQL("create table UserDetails " + "(FName text,LName text,Email text,Username text,Password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Feedback");
        db.execSQL("DROP TABLE IF EXISTS UserDetails");
        onCreate(db);
    }

    public boolean insertFeedback (String comment,String rating) //Insert into feedback table
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

    public boolean insertUserDetails (String FName, String LName, String Email, String Username,String Password) //Insert into user table
    {

        Log.d("Debug -->", "inside insert user of database class");

        //db.execSQL("create table UserDetails " + "(ID integer autoincrement, FName text,LName text,Email text,Username text,Password text)");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("FName", FName);
        contentValues.put("LName", LName);
        contentValues.put("Email", Email);
        contentValues.put("Username", Username);
        contentValues.put("Password", Password);

        db.insert("UserDetails", null, contentValues);

        System.out.println("FName: " +FName);

        return true;
    }

    public boolean Validate(String Username,String Password) //Validate the entered password
    {
        String pass = null;
        String[] args = {Username};
        System.out.println(Username);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from UserDetails where Username like ? " , args );
        System.out.println(res.getCount());
        if(res != null && res.getCount() !=0 && res.moveToFirst())
        {
            pass = res.getString(res.getColumnIndex("Password"));
            System.out.println("Inside DB, Validate, password: " +pass);
        }
        else
        {
            System.out.println("Inside DB, validate, else part");
        }

        if(Password.equals(pass))
        {
            return true;
        }
        else
            return false;
    }

    public Cursor getUserDetails(String username) //Fetching the user details
    {
        String[] args = {username};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from UserDetails where Username like ? " , args );
        return res;
    }


    public int numberOfRows()
    {
        int numRows ;
        SQLiteDatabase db = this.getReadableDatabase();

        numRows = (int) DatabaseUtils.queryNumEntries(db, "Feedback");

        return numRows;
    }

}