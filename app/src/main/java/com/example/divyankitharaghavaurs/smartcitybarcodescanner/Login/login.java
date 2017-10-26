package com.example.divyankitharaghavaurs.smartcitybarcodescanner.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Util.AlertDialogManager;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Database.database;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.HomePage.Dashboard;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.R;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Session.SessionManager;
import com.example.divyankitharaghavaurs.smartcitybarcodescanner.Registration.register;

import java.util.HashMap;

/**
 * Created by divyankithaRaghavaUrs on 10/15/17.
 */

public class login extends AppCompatActivity {


    EditText txtUsername, txtPassword;
    Button btnLogin;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    database mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mydb = new database(this);


        session = new SessionManager(getApplicationContext()); //Creating shared pref to maintain session of the user
        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        btnLogin = (Button) findViewById(R.id.login);



        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                System.out.println("Inside login " +username);

                if(username.trim().length() > 0 && password.trim().length() > 0)
                {
                    loginInfo L = new loginInfo();
                    L.setUname(username);
                    L.setPassword(password);
                    Boolean result = mydb.Validate(L.uname, L.password); //Validating username and password
                    if(result)
                    {
                        System.out.println("going to homepage from login");

                        session.createLoginSession(L.uname, L.email);
                        session = new SessionManager(getApplicationContext());
                        HashMap<String, String> user = session.getUserDetails();
                        System.out.println("Session name after login:" + user.get(SessionManager.KEY_NAME));

                        Intent intent = new Intent(login.this,Dashboard.class);
                        startActivity(intent);
                    }
                    else
                        alert.showAlertDialog(login.this, "Login failed..", "Username/Password is incorrect", false);
                }
                else
                {
                    alert.showAlertDialog(login.this, "Login failed..", "Username/Password is incorrect", false);
                }

            }
        });
    }


    public void gotoRegister(View V)
    {
        Intent intent = new Intent(login.this,register.class);
        startActivity(intent);
    }

    public void finishLogin(View v)
    {
        login.this.finish();
    }
}


