package com.beginners.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends AppCompatActivity {
    String mWord;
    int mFailCounter = 0;
    int mGuessedLetters = 0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);

        String wordSent = getIntent().getStringExtra("WORD_ID"); //To pass words to activities
        Log.d("MYLOG",wordSent);

        mWord = wordSent;

        createTextViews(wordSent);
    }

    /** Retrieving the letter introduced on the editText
     *
     * @param v (button clicked)
     */
    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();
        myEditText.setText("");

        Log.d("MYLOG","The letter is " + letter);

        if (letter.length() == 1) {
            checkLetter(letter.toUpperCase());
        } else {
            Toast.makeText(this, "Please Enter Letter", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Checking if the letter introduced matches any letter in the word to guess
     * @param introducedLetter, letter introduced by the user
     */

    public void checkLetter(String introducedLetter) {
        char charIntroduced = introducedLetter.charAt(0);

        boolean letterGuessed = false;

        for (int i=0; i< mWord.length(); i++) {
            char charFromTheWord = mWord.charAt(i);
            if (charFromTheWord == charIntroduced) {
                Log.d("MYLOG", "There was one match");
                letterGuessed = true;
                showLettersAtIndex(i, charIntroduced);
                mGuessedLetters++;
            }
        }

        if (!letterGuessed) {
            letterFailed(Character.toString(charIntroduced));
        }

        if (mGuessedLetters == mWord.length()) {
            mPoints++;  //Increase the points by one
            //Clear the previous word
            //Start the game again
            clearScreen();
        }
    }

    public void clearScreen(){
        TextView textViewFailed = (TextView) findViewById(R.id.lettersGuessedField);
        textViewFailed.setText("");
        mGuessedLetters = 0;
        mFailCounter = 0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < layoutLetters.getChildCount(); i++) {
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);

    }


    public void createTextViews(String word)
    {
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i=0; i < word.length(); i++) {
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview,null);
            layoutLetters.addView(newTextView);
        }
    }

    /**
     * This is for when a letter fails
     * @param letterFailed, is the letter to add to the lettersGuessedField
     */
    public void letterFailed(String letterFailed) {
        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textViewFailed = (TextView) findViewById(R.id.lettersGuessedField);

        String previousFail = textViewFailed.getText().toString();

        //My initial guess was to use .append rather than .setText. It's shorter than
        //getting the previous string, but I'm following the instructor.
        textViewFailed.setText(previousFail + letterFailed);


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
            //Open a next Activity (screen) by creating a new intent, etc.
            Intent gameOverIntent = new Intent(this,GameOverActivity.class);
            gameOverIntent.putExtra("POINTS_ID", mPoints);
            startActivity(gameOverIntent);
        }
        //imageView.setImage
    }

    /**
     * Displaying a letter guessed by the user
     * @param position of the letter
     * @param letterGuessed the letter that was guessed
     */
    public void showLettersAtIndex(int position, char letterGuessed) {
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }


}
