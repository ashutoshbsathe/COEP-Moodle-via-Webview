package com.sathe.ashutosh.coepmoodle;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static final Integer WRITE_EXST = 0x3;
    static final Integer READ_EXST = 0x4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        String vinfo = BuildConfig.VERSION_NAME;
        vinfo = "App version : "+vinfo;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences settings=getSharedPreferences("initialize", 0);
        boolean isInitialized = settings.getBoolean("Initialized", false);
        if(!isInitialized)
        {
            SharedPreferences profile = getSharedPreferences("userdata", 0);
            SharedPreferences.Editor editor = profile.edit();
            editor.putString("username", "default");
            editor.putString("password", "default");
            editor.putString("LoginFailed","NotLoggedInYet");
            editor.putString("FailedLoginAcc","false");
            editor.commit();
            SharedPreferences.Editor init = settings.edit();
            init.putBoolean("Initialized",true);
            init.commit();
        }
        SharedPreferences profile = getSharedPreferences("userdata", 0);
        String logged = profile.getString("username",null);
        if(logged.equals("default"))
            logged = "Not logged in";
        else
            logged = "Logged in as " + logged;
        TextView loggedIn = (TextView)navigationView.getHeaderView(0).findViewById(R.id.logged);
        loggedIn.setText(logged);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.nav_login)
        {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
        if(id==R.id.nav_moodle)
        {
            Intent login = new Intent(this, Moodle.class);
            startActivity(login);
        }
        if(id==R.id.about)
        {
            Intent login = new Intent(this, About.class);
            startActivity(login);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public int permissioncheck(View view)
    {   int perm = 0;

        if(Build.VERSION.SDK_INT>=23)
            perm = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        else {
            Toast.makeText(getApplicationContext(), "No need to check permissions for android versions before Marshmallow", //To notify the Client that the file is being downloaded
                    Toast.LENGTH_LONG).show();
            return 0;
        }
        if(perm == PackageManager.PERMISSION_DENIED)
        {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXST);

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Storage Permission Is Granted !!", //To notify the Client that the file is being downloaded
                    Toast.LENGTH_LONG).show();
        }
        if(Build.VERSION.SDK_INT>=23)
            perm = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(perm == PackageManager.PERMISSION_DENIED)
        {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXST);

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Storage can be read by app !!", //To notify the Client that the file is being downloaded
                    Toast.LENGTH_LONG).show();
        }
        return 1;

    }
    public void manual(View view)
    {
        Intent i = new Intent(this,ManualActivity.class);
        startActivity(i);
    }
}
