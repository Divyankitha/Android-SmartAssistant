package com.example.divyankitharaghavaurs.smartcitybarcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class Picture extends AppCompatActivity //Activity to click picture and record video
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_VIDEO_CAPTURE = 2;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);

    }

    public void takePicture(View V) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

}



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView i = (ImageView) findViewById(R.id.imageDisplay);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            i.setImageBitmap(imageBitmap);


    }

        VideoView v = (VideoView) findViewById(R.id.videoView);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            v.setVideoURI(videoUri);
            MediaController mediaController = new MediaController(this);
            v.setMediaController(mediaController);
            v.start();
        }

    }

    public void takeVideo(View v)
    {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }



}
