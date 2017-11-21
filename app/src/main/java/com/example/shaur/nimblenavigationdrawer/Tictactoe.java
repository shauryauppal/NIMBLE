package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Tictactoe extends AppCompatActivity {

    boolean myActive=false;
    // false is cross and true is circle

    //my Game state
    int []myGameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    int[][] win = new int[3][3];


    ImageView myimage;

    public void winner1()
    {
        Toast.makeText(this, "Player with circle wins", Toast.LENGTH_LONG).show();
    }
    public void winner2()
    {
        Toast.makeText(this, "Player with cross win", Toast.LENGTH_LONG).show();
    }
    public void check_winner()
    {
        int ct_d1=0,ct_d2=0,count=0;
        for(int i=0;i<3;i++)
        {
            count=0;
            for(int j=0;j<3;j++)
            {
                if(win[i][j]==1)
                {
                    count++;
                }
                else if(win[i][j]==0)
                    count--;

                if(i==j)
                {
                    if(win[i][j]==1)
                    {
                        ct_d1++;
                    }
                    else  if(win[i][j]==0)
                        ct_d1--;

                }
                if(i+j==2)
                {
                    if(win[i][j]==1)
                    {
                        ct_d2++;
                    }
                    if(win[i][j]==0)
                        ct_d2--;
                }

            }
            if(ct_d1==3 || ct_d2==3 || count==3)
            {
                winner1();
                break;
            }
            if(ct_d1==-3 || ct_d2==-3 || count==-3)
            {
                winner2();
                break;
            }
        }

        int ct_1=0,ct_2=0;
        for(int i=0;i<3;i++)
        {
            ct_1=0;ct_2=0;
            for(int j=0;j<3;j++)
            {
                if(win[j][i]==1)
                    ct_1++;
                if(win[j][i]==0)
                    ct_2--;
            }
            if(ct_1==3)
            {
                winner1();
                break;
            }
            else if(ct_2==-3)
            {
                winner2();
                break;
            }
        }
    }
    public void Imagetapped(View view) {
        myimage = (ImageView) view;

        Log.i("Tag is:", "Image number is" + myimage.getTag().toString());

        int tag = Integer.parseInt(myimage.getTag().toString());

        if (myGameState[tag] == -1) {
            if (myActive == false) {
                myimage.setImageResource(R.drawable.cross);
                myimage.animate().rotation(360).setDuration(1000);
                Log.i("Tag","Print:"+ tag/3 + tag%3);
                win[tag/3][tag%3]=0;
                //cross
                myActive = !myActive;
            } else if (myActive == true) {
                myimage.setImageResource(R.drawable.circle);
                Log.i("Tag","Print:"+ tag/3 + tag%3);
                win[tag/3][tag%3]=1;
                myActive = !myActive;
            }
            myGameState[tag] = tag;
            check_winner();

        }
        else
        {
            Toast.makeText(this, "Already filled position", Toast.LENGTH_SHORT).show();
        }
    }

public void resetgame(View view){
    Toast.makeText(Tictactoe.this, "Game reset", Toast.LENGTH_SHORT).show();
    myActive=false;
    for (int i=0;i<myGameState.length;i++)
    {
        myGameState[i]=-1;
    }
    //Change image back to default

    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line1);
    for (int i=0;i<linearLayout.getChildCount();i++){
        ( (ImageView) linearLayout.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
    }

    LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.line2);
    for (int i=0;i<linearLayout2.getChildCount();i++){
        ( (ImageView) linearLayout2.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
    }

    LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.line3);
    for (int i=0;i<linearLayout3.getChildCount();i++){
        ( (ImageView) linearLayout3.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
    }

    for(int i=0;i<3;i++)
    {
        for(int j=0;j<3;j++)
            win[i][j]=-1;
    }
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);
        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                win[i][j]=-1;
            }
        }

    }
}
