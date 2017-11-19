package com.example.shaur.nimblenavigationdrawer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by shaur on 13-09-2017.
 */


public class EarthAdapter extends ArrayAdapter<Earth> {

    public EarthAdapter(Activity context, ArrayList<Earth> earthquakes) {
        super(context, 0, earthquakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, parent, false);
        }

        //Listitem position
        Earth currentdata = getItem(position);

        //For Earth text display
        TextView Mag = (TextView) v.findViewById(R.id.magnitude);
        Mag.setText(String.valueOf(currentdata.getMagnitude()));


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) Mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentdata.getMagnitude());
        //Log.e("Error","Check->"+currentdata.getMagnitude()+" Color->"+magnitudeColor);
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //For Location
        TextView Loc = (TextView) v.findViewById(R.id.locationoffset);
        Loc.setText(currentdata.getcity1());

        TextView Location = (TextView) v.findViewById((R.id.locationprimary));

        Location.setText(currentdata.getcity2());

        //For date
        TextView Date = (TextView) v.findViewById(R.id.date);
        Date.setText(currentdata.getDate());

        //For time
        TextView Time = (TextView) v.findViewById(R.id.time);
        Time.setText(currentdata.getTime());


        return v;


    }




    private int getMagnitudeColor(double magnitude) {
        int magnitudeFloor = (int) Math.floor(magnitude);
        int magnitudeReturn;
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeReturn=R.color.magnitude1;
            break;
            case 2:
                magnitudeReturn= R.color.magnitude2;
            break;
            case 3:
                magnitudeReturn=R.color.magnitude3;
            break;
            case 4:
                magnitudeReturn=R.color.magnitude4;
            break;
            case 5:
                magnitudeReturn=R.color.magnitude5;
            break;
            case 6:
                magnitudeReturn=R.color.magnitude6;
            break;
            case 7:
                magnitudeReturn=R.color.magnitude7;
            break;
            case 8:
                magnitudeReturn=R.color.magnitude8;
            break;
            case 9:
                magnitudeReturn=R.color.magnitude9;
            break;
            default:
                magnitudeReturn=R.color.magnitude10plus;
            break;
        }

        return ContextCompat.getColor(getContext(), magnitudeReturn);
    }

}
