package com.beginners.hangdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    String mword = "WORD";

    int mFailCounter = 0;

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

        if (letter.length() == 1) {
            checkLetter(letter);
        } else {
            Toast.makeText(this, "Please Enter Letter", Toast.LENGTH_SHORT).show();
        }

        myEditText.setText(null);
    }

    /**
     * Checking if the letter introduced matches any letter in the word to guess
     * @param introducedLetter, letter introduced by the user
     */

    public void checkLetter(String introducedLetter) {
        char charIntroduced = introducedLetter.charAt(0);

        boolean letterGuessed = false;

        for (int i=0; i< mword.length(); i++) {
            char charFromTheWord = mword.charAt(i);
            if (charFromTheWord == charIntroduced) {
                Log.d("MYLOG", "There was one match");
                letterGuessed = true;
                showLettersAtIndex(i, charIntroduced);
            }
        }

        if (!letterGuessed) {
            letterFailed(Character.toString(charIntroduced));
        }


    }

    public void letterFailed(String letterFailed) {
        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView = (TextView) findViewById(R.id.lettersGuessedField);

        String previousFail = textView.getText().toString();

        textView.setText(previousFail + letterFailed);


        if ( mFailCounter == 1) {
            imageView.setImageResource(R.drawable.hangdroid_1);
        } else if (mFailCounter == 2) {
            imageView.setImageResource(R.drawable.hangdroid_2);
        } else if (mFailCounter == 3) {
            imageView.setImageResource(R.drawable.hangdroid_3);
        } else if (mFailCounter == 4) {
            imageView.setImageResource(R.drawable.hangdroid_4);
        } else if (mFailCounter == 5) {
            imageView.setImageResource(R.drawable.hangdroid_5);
        } else if (mFailCounter == 6) {
            //TODO GAME OVER
            imageView.setImageResource(R.drawable.game_over);
        }
        //imageView.setImage
    }

    /**
     * Displaying a letter guessed by the user
     * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed) {
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt(position);

        textView.setText(Character.toString(letterGuessed));
    }


}
