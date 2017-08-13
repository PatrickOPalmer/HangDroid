package com.beginners.hangdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    /** Retrieving the letter introduced on the editText
     *
     * @param v (button clicked)
     */
    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();

        Log.d("MYLOG","The letter is " + letter);

    }


}
