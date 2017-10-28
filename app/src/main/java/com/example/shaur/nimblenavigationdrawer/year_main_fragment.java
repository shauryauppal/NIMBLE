package com.example.shaur.nimblenavigationdrawer;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class year_main_fragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_main_fragment);
        //get position value
        Intent intent = getIntent();
        String position = intent.getStringExtra(Intent.EXTRA_TEXT);
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);


        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        if(position=="0")
        {
            viewPager.setCurrentItem(0);
        }
        else if(position=="1")
        {
            viewPager.setCurrentItem(1);
        }
        else if(position=="2"){
            viewPager.setCurrentItem(2);
        }
        else if(position=="3")
        {
            viewPager.setCurrentItem(3);
        }
    }
}
