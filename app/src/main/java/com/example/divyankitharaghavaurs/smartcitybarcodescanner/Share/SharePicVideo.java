package com.example.divyankitharaghavaurs.smartcitybarcodescanner.Share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ShareActionProvider;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class SharePicVideo extends AppCompatActivity //This activity is used to provide the share on social media feature
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);

    }


    public static final int RESULT_GALLERY = 0;

    public void shareDisplay(View V)
    {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //send the user to gallery to choose picture/video to share
        if (galleryIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(galleryIntent , RESULT_GALLERY );
        }
    }

    Uri imageUri;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView image = (ImageView) findViewById(R.id.imageShare);
        if (requestCode == RESULT_GALLERY && resultCode == RESULT_OK)
        {
            imageUri = data.getData(); //obtain the image/video and display on the screen
            image.setImageURI(imageUri);

        }

    }

    public void shareSocial(View v) //Function to share image/video on social media
    {
        System.out.println("Inside share function");
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        Uri screenshotUri = imageUri;

        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        sharingIntent.setType("image/png");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        startActivity(Intent.createChooser(sharingIntent, "Share image using")); //Provide the share on social media feature
        System.out.println("After sending intent to share");
    }

}
