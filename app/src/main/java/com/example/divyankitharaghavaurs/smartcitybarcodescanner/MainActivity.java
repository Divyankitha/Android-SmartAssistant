package com.example.divyankitharaghavaurs.smartcitybarcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity //Opening page activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoHomePage(View V)
    {
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        startActivity(intent);
    }
}
