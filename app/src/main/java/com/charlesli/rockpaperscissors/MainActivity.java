package com.charlesli.rockpaperscissors;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.charlesli.common.Constants;


public class MainActivity extends ActionBarActivity{

    private EditText name;
    private Button gobutton;
    private SharedPreferences sharedInfo;
    private String savedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedInfo = getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);
        //savedName = sharedInfo.getString("NAME", "Charles");


        name = (EditText) findViewById(R.id.editTextResult);
        gobutton = (Button) findViewById(R.id.buttonStop);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rock Paper and Scissors");
        actionBar.setDisplayHomeAsUpEnabled(true);


        gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")) {
                    SharedPreferences.Editor editor = sharedInfo.edit();
                    editor.putString(Constants.NAMES_PREF, name.getText().toString());
                    editor.commit();

                    Intent screen = new Intent(MainActivity.this, OptionsActivity.class);
                    //screen.putExtra("NAME", name.getText().toString());
                    startActivity(screen);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input a name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if (id == android.R.id.home) {
            onBackPressed();
            System.out.println("Back button pressed");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to quit")
                .setMessage("Really??")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
        System.out.println("Back button pressed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Resumed");
    }
}
