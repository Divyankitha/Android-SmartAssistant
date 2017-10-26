package com.example.divyankitharaghavaurs.smartcitybarcodescanner.UserProfile;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Database.database;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Session.SessionManager;

import java.util.HashMap;

/**
 * Created by divyankithaRaghavaUrs on 10/21/17.
 */

public class profile extends AppCompatActivity //Activity to display user's profile
{
    SessionManager session;
    database mydb;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        mydb = new database(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String User = user.get(SessionManager.KEY_NAME);
        Cursor result = mydb.getUserDetails(User);
        if(result != null && result.getCount() !=0 && result.moveToFirst())
        {
            TextView fn = (TextView)findViewById(R.id.FirstName);
            TextView ln = (TextView)findViewById(R.id.LastName);
            TextView un = (TextView)findViewById(R.id.DisplayUname);
            TextView em = (TextView)findViewById(R.id.Email);

            fn.setText(result.getString(result.getColumnIndex("FName")));
            ln.setText(result.getString(result.getColumnIndex("LName")));
            un.setText(result.getString(result.getColumnIndex("Username")));
            em.setText(result.getString(result.getColumnIndex("Email")));
        }
    }


    public void signout(View v)
    {
        session = new SessionManager(getApplicationContext());
        session.logoutUser();

    }


    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }


}
