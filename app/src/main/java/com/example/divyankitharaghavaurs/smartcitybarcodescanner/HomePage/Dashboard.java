package com.example.divyankitharaghavaurs.smartcitybarcodescanner.HomePage;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.BarCode.About;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.BarCode.Scan;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.UserFeedback.Feedback;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Location.LocateMap;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Picture.Picture;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Registration.register;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Session.SessionManager;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Share.SharePicVideo;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.UserProfile.profile;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Util.AlertDialogManager;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.AudioRecord.audio;

import java.util.HashMap;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class Dashboard extends AppCompatActivity//This is the main homepage activity
{

    private static final int PERMISSION_REQUEST_CODE = 1;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


       if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {

            } else
            {
                Log.d("permission", "permission denied for maps - requesting it");
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }

        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);

        if(firstRun==false)//if running for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.commit();
            Intent i=new Intent(Dashboard.this,register.class);
            startActivity(i);
            finish();
        }
        else
        {
            setContentView(R.layout.home_page);
            session = new SessionManager(getApplicationContext());
            Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

            session.checkLogin();

            HashMap<String, String> user = session.getUserDetails();
            String name = user.get(SessionManager.KEY_NAME);
            String email = user.get(SessionManager.KEY_EMAIL);

            System.out.println("username:" +name);
            System.out.println("email:" +email);

        }

    }


    @Override
    protected void onStart()
    {
        super.onStart();
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {

            } else
            {
                Log.d("permission", "permission denied for maps - requesting it");
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        }*/

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

    //following functions are all on click functions which leads to various features

    public void gotoAboutUs(View v)
    {
        Intent intent = new Intent(Dashboard.this, About.class);
        startActivity(intent);
    }

    public void gotoScan(View v)
    {
        Intent intent = new Intent(Dashboard.this, Scan.class);
        startActivity(intent);
    }

    public void gotoPicture(View v)
    {
        Intent intent = new Intent(Dashboard.this, Picture.class);
        startActivity(intent);
    }

    public void gotoShare(View v)
    {
        Intent intent = new Intent(Dashboard.this, SharePicVideo.class);
        startActivity(intent);
    }

    public void gotoLocation(View v)
    {
        Intent intent = new Intent(Dashboard.this, LocateMap.class);
        startActivity(intent);
    }

    public void gotoFeedback(View v)
    {
        Intent intent = new Intent(Dashboard.this, Feedback.class);
        startActivity(intent);
    }

    public void gotoProfile(View v)
    {
        Intent intent = new Intent(Dashboard.this,profile.class);
        startActivity(intent);
    }

    public void gotoAudio(View v)
    {
        Intent intent = new Intent(Dashboard.this,audio.class);
        startActivity(intent);
    }

    public void onClicklogout(View arg0)
    {

        session.logoutUser();
    }


    public void finishActivityA(View v)
    {
        Dashboard.this.finish();
    }

}
