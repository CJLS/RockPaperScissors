package com.charlesli.rockpaperscissors;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class OptionsActivity extends ActionBarActivity {

    private ImageView mRockView;
    private ImageView mPaperView;
    private ImageView mScissorsView;
    private TextView mSelectedOptionsTextView;
    private TextView mResultTextView;
    private Random mRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        mRockView = (ImageView) findViewById(R.id.rock);
        mPaperView = (ImageView) findViewById(R.id.paper);
        mScissorsView = (ImageView) findViewById(R.id.scissors);
        mSelectedOptionsTextView = (TextView) findViewById(R.id.selectedOptionTextView);
        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        //testing1233



        mRockView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedOptionsTextView.setText("Rock!!");

                if (randomNum() == 0) {
                    mResultTextView.setText("It's a Tie!!!!");
                }
                else if (randomNum() == 1) {
                    mResultTextView.setText("You Lose!!!");
                }
                else if (randomNum() == 2) {
                    mResultTextView.setText("You Win!!!");
                }
            }
        });

        mPaperView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedOptionsTextView.setText("Paper!!");
                if (randomNum() == 0) {
                    mResultTextView.setText("You Win!!!");
                }
                else if (randomNum() == 1) {
                    mResultTextView.setText("It's a Tie!!!!");
                }
                else if (randomNum() == 2) {
                    mResultTextView.setText("You Lose!!!");
                }
            }
        });

        mScissorsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedOptionsTextView.setText("Scissors");
                if (randomNum() == 0) {
                    mResultTextView.setText("You Lose!!!");
                }
                else if (randomNum() == 1) {
                    mResultTextView.setText("You Win!!!");
                }
                else if (randomNum() == 2) {
                    mResultTextView.setText("It's a Tie!!!!");
                }
            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    public int randomNum() {
        return mRandom.nextInt(3);

    }

}
