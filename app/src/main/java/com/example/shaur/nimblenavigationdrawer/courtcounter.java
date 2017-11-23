package com.example.shaur.nimblenavigationdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class courtcounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courtcounter);
    }
    int scoreA=0,scoreB=0;

    public void increment_one(View view)
    {
        scoreA++;
        displayForTeamA(scoreA);
    }
    public void increment_two(View view)
    {
        scoreA+=2;
        displayForTeamA(scoreA);
    }
    public void increment_three(View view)
    {
        scoreA+=3;
        displayForTeamA(scoreA);
    }
    public void increment_oneB(View view)
    {
        scoreB++;
        displayForTeamB(scoreB);
    }
    public void increment_twoB(View view)
    {
        scoreB+=2;
        displayForTeamB(scoreB);
    }
    public void increment_threeB(View view)
    {
        scoreB+=3;
        displayForTeamB(scoreB);
    }

    public void reset(View view)
    {
        scoreA=0;
        scoreB=0;
        displayForTeamA(scoreA);
        displayForTeamB(scoreB);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
