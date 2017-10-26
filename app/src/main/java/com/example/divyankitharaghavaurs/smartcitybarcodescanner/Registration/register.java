package com.example.divyankitharaghavaurs.smartcitybarcodescanner.Registration;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Util.AlertDialogManager;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Database.database;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.HomePage.Dashboard;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Session.SessionManager;

/**
 * Created by divyankithaRaghavaUrs on 10/15/17.
 */

public class register extends AppCompatActivity
{
    database mydb;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    public static String contents = null;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mydb = new database(this);
        session = new SessionManager(getApplicationContext());
    }

    public void onClickScan(View V) //Registartion using QR code
    {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe)
        {
            //on catch, show the download dialog
            showDialog(register.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
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

    public void onActivityResult(int requestCode, int resultCode, Intent intent) //Displaying the data got from QR code scanning
    {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                //setContentView(R.layout.scan_result);
               // TextView T = (TextView) findViewById(R.id.testDisplay);
                contents = intent.getStringExtra("SCAN_RESULT");
                System.out.println(contents);
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                toast.show();
                displayContent(contents);
                //T.setText(contents);
            }
        }
    }

    public void displayContent(String content)
    {
        String[] parts;
        parts = content.split("\n");
        //System.out.println(parts[0]);
        EditText fname = (EditText) findViewById(R.id.fname);
        EditText lname = (EditText) findViewById(R.id.lname);
        EditText eid = (EditText) findViewById(R.id.eid);
        fname.setText(parts[0]);
        lname.setText(parts[1]);
        eid.setText(parts[2]);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void gotoLogin(View V) throws  Exception
    {

        EditText fn =(EditText)findViewById(R.id.fname);
        EditText ln = (EditText)findViewById(R.id.lname);
        EditText eid = (EditText)findViewById(R.id.eid);
        EditText un =(EditText)findViewById(R.id.uname);
        EditText pass = (EditText)findViewById(R.id.pass);

        userInfo U = new userInfo();
        U.setFname(fn.getText().toString());
        U.setLname(ln.getText().toString());
        U.setEmail(eid.getText().toString());
        U.setUname(un.getText().toString());
        U.setPassword(pass.getText().toString());

        boolean result = mydb.insertUserDetails(U.fname, U.lname,U.email,U.uname,U.password);
        if(result)
        {
            Log.d("Debug -->", "result =" + result);
            Toast.makeText(register.this,"Registration successfull!", Toast.LENGTH_LONG).show();

            session.createLoginSession(U.uname, U.email);
            session = new SessionManager(getApplicationContext());

            Intent intent = new Intent(register.this, Dashboard.class);
            startActivity(intent);

        }

    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void finishRegister(View V)
    {
        register.this.finish();
    }
}
