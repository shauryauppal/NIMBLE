package com.example.shaur.nimblenavigationdrawer;

import android.text.method.HideReturnsTransformationMethod;

/**
 * Created by shaur on 13-09-2017.
 */

public class Earth {
    //Earthquake magnitude
    private double Magnitude;
    //Location
    private String city1;
    private String city2;
    //date
    private  String date;
    // time
    private String time;
    private String url;
    /**
     *
     * @param mMag Magnitude
     * @param mCity1 Location
     * @param mCity2 Location
     * @param mDate When it occured
     */
    public Earth(double mMag,String mCity1,String mCity2,String mDate,String mTime,String mUrl)
    {
        Magnitude=mMag;
        city1=mCity1;
        city2=mCity2;
        date=mDate;
        time=mTime;
        url=mUrl;

    }

    /**
     *
     * @return Magnitutde of earthquake
     */
    public double getMagnitude()
    {
        return Magnitude;
    }

    /**
     *
     * @return Location of Earthquake
     */
    public String getcity1()
    {
        return city1;
    }
    public String getcity2()
    {
        return city2;
    }
    /**
     *
     * @return Date
     */
    public String getDate()
    {
        return date;
    }

    /**
     *
     * @return time of Earthquake
     */
    public String getTime(){return time;}

    /**
     *
     * @return Url of Earth quake selected
     */
    public String getUrl(){return url;}
}
