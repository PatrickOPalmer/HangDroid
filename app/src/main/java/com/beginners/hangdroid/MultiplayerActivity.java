package com.beginners.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
    }

    public void startMultiGame(View v) {
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        String wordToGuess = editText.getText().toString();

        Intent myIntent = new Intent(this, GameMultiActivity.class);
        myIntent.putExtra("WORD_ID", wordToGuess);

        startActivity(myIntent);
    }
}
