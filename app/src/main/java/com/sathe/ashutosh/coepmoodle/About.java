package com.sathe.ashutosh.coepmoodle;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "2ashutoshbs@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding the COEP Moodle App");
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    Snackbar.make(view, "Email app not found !", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }



            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView aboutVersion = (TextView)findViewById(R.id.about_version);
        String vInfo = BuildConfig.VERSION_NAME;
        vInfo = "COEP Moodle App Version : "+vInfo;
        aboutVersion.setText(vInfo);
    }
    public void li(View view)
    {
        new LibsBuilder()
                //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withAboutAppName("COEP Moodle")
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withAboutDescription(getString(R.string.about))
                //start the activity
                .start(this);
    }

    public void github(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/Ashutosh-01/android_app_coep_moodle"));
        startActivity(intent);
    }
}
