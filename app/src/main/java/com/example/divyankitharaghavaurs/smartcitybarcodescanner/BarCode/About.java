package com.example.divyankitharaghavaurs.smartcitybarcodescanner.BarCode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class About extends AppCompatActivity
{
    Scan s = new Scan();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
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
        TextView T = (TextView) findViewById(R.id.about_display); // Displays the information about the 3D tree obtained after scanning QR code
        if(Scan.contents == null)
        {
            T.setText("Please Scan the QR code first!");
            System.out.println("if is true");
        }
        else
        {
            T.setText(Scan.contents);
            System.out.println("else is true");
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

    public void gotoScan(View V)
    {
        Intent i=new Intent(About.this,Scan.class);
        startActivity(i);
    }

    public void finishAboutUs(View v)
    {
        About.this.finish();
    }
}
