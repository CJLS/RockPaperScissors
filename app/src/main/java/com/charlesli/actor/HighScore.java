package com.charlesli.actor;

/**
 * Created by Li on 2015/6/6.
 */
public class HighScore {

    private String mName;
    private int mScore;

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public HighScore(String name, int score) {
        mName = name;
        mScore = score;

    }
}
