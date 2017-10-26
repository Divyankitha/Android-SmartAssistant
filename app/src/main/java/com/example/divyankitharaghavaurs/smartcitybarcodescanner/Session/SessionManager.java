package com.example.divyankitharaghavaurs.smartcitybarcodescanner.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Login.login;

import java.util.HashMap;

/**
 * Created by divyankithaRaghavaUrs on 10/15/17.
 */

public class SessionManager //Session manager using shared preference
{
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";


    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String email) //Creating login session
    {
        System.out.println("Inside create login session");
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        System.out.println("Inside create login session: uname: "+KEY_NAME);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }


    public void checkLogin() //Checking if user has logged in or not
    {

        if(!this.isLoggedIn())
        {

            Intent i = new Intent(_context, login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }


    public HashMap<String, String> getUserDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }


    public void logoutUser() //Removing teh user once the user logs out
    {

        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
