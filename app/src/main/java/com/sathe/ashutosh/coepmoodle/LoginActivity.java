package com.sathe.ashutosh.coepmoodle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.sathe.ashutosh.coepmoodle.R.layout.activity_login;
import static com.sathe.ashutosh.coepmoodle.R.layout.content_login;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences profile = getSharedPreferences("userdata",0);
        SharedPreferences.Editor editor = profile.edit();
        String checkIfLoginFailed = profile.getString("LoginFailed",null);
        if(checkIfLoginFailed.equalsIgnoreCase("True"))
        {
            editor.putString("username","hahaha");
            editor.putString("password","hahaha");
            editor.commit();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences profile = getSharedPreferences("userdata",0);
        SharedPreferences.Editor editor = profile.edit();
        String username = profile.getString("username",null);
        String checkIfLoginFailed = profile.getString("LoginFailed",null);
        if(checkIfLoginFailed.equalsIgnoreCase("True"))
        {
            Toast.makeText(this, "Login Was Unsuccessful ! Please Try Again", Toast.LENGTH_LONG).show();
            editor.putString("LoginFailed","False");
            editor.putString("username","hahaha");
            editor.putString("password","hahaha");
            editor.commit();
        }
        TextView textView = (TextView) findViewById(R.id.saved_user);
        if(username.equalsIgnoreCase("hahaha"))
            textView.setText("No user is currently saved");
        else
            textView.setText(username);

    }
    public void login(View view)
    {
        EditText uname=(EditText) findViewById(R.id.username);
        String username = uname.getText().toString();
        EditText pass = (EditText) findViewById(R.id.password);
        String password = pass.getText().toString();
        SharedPreferences profile = getSharedPreferences("userdata",0);
        SharedPreferences.Editor editor = profile.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
        Intent i = new Intent(this, Moodle.class);
        /*Bundle profile = new Bundle();
        profile.putString("username", username);
        profile.putString("password", password);
        i.putExtras(profile);*/
        startActivity(i);
    }
}
