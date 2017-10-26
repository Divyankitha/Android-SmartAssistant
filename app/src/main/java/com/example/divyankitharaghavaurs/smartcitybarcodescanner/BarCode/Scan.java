package com.example.divyankitharaghavaurs.smartcitybarcodescanner.BarCode;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;

/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class Scan extends AppCompatActivity //activity to scan QR code
{
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public static String contents = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
            } catch (ActivityNotFoundException anfe)
           {
            //on catch, show the download dialog
            showDialog(Scan.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
            }


    }

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo)
    {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                    } catch (ActivityNotFoundException anfe)
                {

                }
                }
            });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        return downloadDialog.show();
        }

            //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                setContentView(R.layout.scan_result);
                TextView T = (TextView) findViewById(R.id.displayResult);
                contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                toast.show();
                T.setText(contents);

                }
            }
        }



}
