package com.example.divyankitharaghavaurs.smartcitybarcodescanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class HomePage extends AppCompatActivity //This is the main homepage activity
{
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                //
            } else
            {
                Log.d("permission", "permission denied for maps - requesting it");
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);


            }
        }

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

    public void gotoAboutUs(View v)
    {
        Intent intent = new Intent(HomePage.this, About.class);
        startActivity(intent);
    }

    public void gotoScan(View v)
    {
        Intent intent = new Intent(HomePage.this, Scan.class);
        startActivity(intent);
    }

    public void gotoPicture(View v)
    {
        Intent intent = new Intent(HomePage.this, Picture.class);
        startActivity(intent);
    }

    public void gotoShare(View v)
    {
        Intent intent = new Intent(HomePage.this, Share.class);
        startActivity(intent);
    }

    public void gotoLocation(View v)
    {
        Intent intent = new Intent(HomePage.this, LocateMap.class);
        startActivity(intent);
    }

    public void gotoFeedback(View v)
    {
        Intent intent = new Intent(HomePage.this, Feedback.class);
        startActivity(intent);
    }



    public void finishActivityA(View v)
    {
        HomePage.this.finish();
    }

}
