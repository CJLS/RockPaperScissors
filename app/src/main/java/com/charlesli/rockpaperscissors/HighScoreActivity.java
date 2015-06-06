package com.charlesli.rockpaperscissors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.charlesli.actor.HighScore;
import com.charlesli.db.DataBaseHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class HighScoreActivity extends ActionBarActivity {

    private ListView mHighScoresList;
    private ListAdapter mListAdapter;
    private ArrayList<HighScore> mHighScores;
    Typeface mFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        mFont = Typeface.createFromAsset(getAssets(), "pokemon_solid.ttf");

        mHighScoresList = (ListView) findViewById(R.id.highScoresList);
        mListAdapter = new ListAdapter(this);
        mHighScores = new ArrayList<HighScore>();


        String url = "http://10.10.190.52/get_scores.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jArray = new JSONArray(response);

                    JSONObject jObj = null;
                    for (int i = 0; i < jArray.length(); i++) {
                        jObj = jArray.getJSONObject(i);
                        System.out.println("Name is " + jObj.getString("name"));
                        System.out.println("Score is " + jObj.getString("score"));
                        mHighScores.add(new HighScore(jObj.getString("name"), jObj.getInt("score")));
                    }
                    mHighScoresList.setAdapter(mListAdapter);
                }
                catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //queue.add(stringRequest);

        DataBaseHelper dbHelper = new DataBaseHelper(this);

        try {
            dbHelper.createDataBase();
        }catch (Exception e) {
            System.out.println("Exception went wrong " + e.toString());
        }

        try {
            dbHelper.openDataBase();
            mHighScores = dbHelper.getHighScores();
            mHighScoresList.setAdapter(mListAdapter);
            dbHelper.close();
        }catch (Exception e) {
            System.out.println("Exception went wrong " + e.toString());
        }


        mHighScoresList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHighScores.remove(position);
                mHighScoresList.setAdapter(mListAdapter);

                //CheckBox ch = (CheckBox) view;
                //ch.setChecked(true);
                // switch page with intent

            }
        });
    }

    public class ListAdapter extends BaseAdapter {

        public Activity mActivity;
        public LayoutInflater mInflater = null;

        public ListAdapter(Activity a) {
            mActivity = a;
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mHighScores.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_layout, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.name_tv);
            TextView score = (TextView) convertView.findViewById(R.id.score_tv);

            name.setTypeface(mFont);

            name.setText(mHighScores.get(position).getName());
            score.setText(Integer.toString(mHighScores.get(position).getScore()));

            return convertView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_score, menu);
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
}
