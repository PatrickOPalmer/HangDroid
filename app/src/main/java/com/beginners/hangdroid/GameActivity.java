package com.beginners.hangdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    String mWord;
    int mFailCounter = 0;
    int mGuessedLetters = 0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomWord();
    }

    /** Retrieving the letter introduced on the editText
     *
     * @param v (button clicked)
     */
    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();
        myEditText.setText("");

        Log.d("MYLOG","The letter is " + letter + " GameActivity line 39.");

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
                Log.d("MYLOG", "There was one match in GameActivity line 61.");
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
            Toast.makeText(this, "Congratulations! The Word was " + mWord, Toast.LENGTH_LONG).show();
            clearScreen();
        }
    }

    public void setRandomWord() {
        String words = "ABLE ACID AGED ALSO AREA ARMY AWAY BABY BACK BALL BAND BANK BASE BATH BEAR BEAT BEEN BEER BELL BELT BEST BILL BIRD BLOW BLUE BOAT BODY BOMB BOND BONE BOOK BOOM BORN BOSS BOTH BOWL BULK BURN BUSH BUSY CALL CALM CAME CAMP CARD CARE CASE CASH CAST CELL CHAT CHIP CITY CLUB COAL COAT CODE COLD COME COOK COOL COPE COPY CORE COST CREW CROP DARK DATA DATE DAWN DAYS DEAD DEAL DEAN DEAR DEBT DEEP DENY DESK DIAL DICK DIET DISC DISK DOES DONE DOOR DOSE DOWN DRAW DREW DROP DRUG DUAL DUKE DUST DUTY EACH EARN EASE EAST EASY EDGE ELSE EVEN EVER EVIL EXIT FACE FACT FAIL FAIR FALL FARM FAST FATE FEAR FEED FEEL FEET FELL FELT FILE FILL FILM FIND FINE FIRE FIRM FISH FIVE FLAT FLOW FOOD FOOT FORD FORM FORT FOUR FREE FROM FUEL FULL FUND GAIN GAME GATE GAVE GEAR GENE GIFT GIRL GIVE GLAD GOAL GOES GOLD GOLF GONE GOOD GRAY GREW GREY GROW GULF HAIR HALF HALL HAND HANG HARD HARM HATE HAVE HEAD HEAR HEAT HELD HELL HELP HERE HERO HIGH HILL HIRE HOLD HOLE HOLY HOME HOPE HOST HOUR HUGE HUNG HUNT HURT IDEA INCH INTO IRON ITEM JACK JANE JEAN JOHN JOIN JUMP JURY JUST KEEN KEEP KENT KEPT KICK KILL KIND KING KNEE KNEW KNOW LACK LADY LAID LAKE LAND LANE LAST LATE LEAD LEFT LESS LIFE LIFT LIKE LINE LINK LIST LIVE LOAD LOAN LOCK LOGO LONG LOOK LORD LOSE LOSS LOST LOVE LUCK MADE MAIL MAIN MAKE MALE MANY MARK MASS MATT MEAL MEAN MEAT MEET MENU MERE MIKE MILE MILK MILL MIND MINE MISS MODE MOOD MOON MORE MOST MOVE MUCH MUST NAME NAVY NEAR NECK NEED NEWS NEXT NICE NICK NINE NONE NOSE NOTE OKAY ONCE ONLY ONTO OPEN ORAL OVER PACE PACK PAGE PAID PAIN PAIR PALM PARK PART PASS PAST PATH PEAK PICK PINK PIPE PLAN PLAY PLOT PLUG PLUS POLL POOL POOR PORT POST PULL PURE PUSH RACE RAIL RAIN RANK RARE RATE READ REAL REAR RELY RENT REST RICE RICH RIDE RING RISE RISK ROAD ROCK ROLE ROLL ROOF ROOM ROOT ROSE RULE RUSH RUTH SAFE SAID SAKE SALE SALT SAME SAND SAVE SEAT SEED SEEK SEEM SEEN SELF SELL SEND SENT SEPT SHIP SHOP SHOT SHOW SHUT SICK SIDE SIGN SITE SIZE SKIN SLIP SLOW SNOW SOFT SOIL SOLD SOLE SOME SONG SOON SORT SOUL SPOT STAR STAY STEP STOP SUCH SUIT SURE TAKE TALE TALK TALL TANK TAPE TASK TEAM TECH TELL TEND TERM TEST TEXT THAN THAT THEM THEN THEY THIN THIS THUS TILL TIME TINY TOLD TOLL TONE TONY TOOK TOOL TOUR TOWN TREE TRIP TRUE TUNE TURN TWIN TYPE UNIT UPON USED USER VARY VAST VERY VICE VIEW VOTE WAGE WAIT WAKE WALK WALL WANT WARD WARM WASH WAVE WAYS WEAK WEAR WEEK WELL WENT WERE WEST WHAT WHEN WHOM WIDE WIFE WILD WILL WIND WINE WING WIRE WISE WISH WITH WOOD WORD WORE WORK YARD YEAH YEAR YOUR ZERO ZONE";


        String[] arrayWords = words.split(" ");

        Log.d("MYLOG", "The array length " + arrayWords.length + " in GameActivity line 84.");

        int randomNumber = (int)(Math.random() * arrayWords.length); //Math.random returns 0.1-0.9, multiply by number of elements needed. Cast to (int)

        String randomWord = arrayWords[randomNumber];

        mWord = randomWord;

        Log.d("MYLOG", "The random word is " + randomWord);
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
            finish();
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
