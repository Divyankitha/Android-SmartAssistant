package com.example.divyankitharaghavaurs.smartcitybarcodescanner.UserFeedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Database.database;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.HomePage.Dashboard;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class Feedback extends AppCompatActivity //This activity is used to obtin feedback from user
{
    database mydb;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
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
    }


    public void submit(View v)
    {
        EditText comment =(EditText)findViewById(R.id.comment);
        EditText rating = (EditText)findViewById(R.id.rating);
        boolean result = mydb.insertFeedback(comment.getText().toString(), rating.getText().toString()); //Calling insert DB function
        if(result)
        {
            Log.d("Debug -->", "result =" + result);
            Toast.makeText(Feedback.this,"Submitted your feedback, Thank you!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Feedback.this, Dashboard.class);
            startActivity(intent);

        }
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

    public void finishFeedback(View v)
    {
        Feedback.this.finish();
    }
}
