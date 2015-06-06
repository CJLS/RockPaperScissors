package com.charlesli.rockpaperscissors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.charlesli.common.Constants;

import java.util.Random;


public class OptionsActivity extends ActionBarActivity {

    private ImageView mRockView;
    private ImageView mPaperView;
    private ImageView mScissorsView;
    private TextView mSelectedOptionsTextView;
    private TextView mResultTextView;
    private Random mRandom;
    private String name;

    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;
    private SharedPreferences sharedInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        sharedInfo = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        name = sharedInfo.getString(Constants.NAMES_PREF, Constants.DEFAULT_NAME);

        mRockView = (ImageView) findViewById(R.id.rock);
        mPaperView = (ImageView) findViewById(R.id.paper);
        mScissorsView = (ImageView) findViewById(R.id.scissors);
        mSelectedOptionsTextView = (TextView) findViewById(R.id.selectedOptionTextView);
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        //testing1233
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            name = bundle.getString("NAME");
//        }



        mRockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedOptionsTextView.setText("Rock!!");
                calculate(ROCK);
            }
        });

        mPaperView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedOptionsTextView.setText("Paper!!");
                calculate(PAPER);
            }
        });

        mScissorsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedOptionsTextView.setText("Scissors");
                calculate(SCISSORS);
            }
        });
    }

    public void calculate(int n) {
        int compChoice = randomNum();

        if (compChoice == n) {
            mResultTextView.setText(name + " It's a Tie!!!!");
        }
        else if (compChoice == ROCK && n == SCISSORS
                || compChoice == PAPER && n == ROCK
                || compChoice == SCISSORS && n == PAPER) {
            mResultTextView.setText(name + " You Lose!!!");
        }
        else if (compChoice == ROCK && n == PAPER
                || compChoice == PAPER && n == SCISSORS
                || compChoice == SCISSORS && n == ROCK) {
            mResultTextView.setText(name + " You Win!!!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
        else if (id == R.id.high_scores) {
            mResultTextView.setText("HI");
            Intent in = new Intent(OptionsActivity.this, HighScoreActivity.class);
            startActivity(in);
        }
        else if (id == android.R.id.home) {
            //empty
        }

        return super.onOptionsItemSelected(item);
    }

    public int randomNum() {
        mRandom = new Random();
        return mRandom.nextInt(3);

    }

}
