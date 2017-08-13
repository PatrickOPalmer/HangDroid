package com.beginners.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayerGame(View v) {
        Intent myIntent = new Intent(this,GameActivity.class); //Create intent for the other class
        startActivity(myIntent);    //Start the Intent for the other Java class
    }

    public void startMultiPlayerGame(View v) {
        Intent myIntent = new Intent(this, GameActivity.class);
        startActivity(myIntent);
    }

    public void displayScores(View v) {
        Intent myIntent = new Intent(this, GameActivity.class);
        startActivity(myIntent);
    }
}
