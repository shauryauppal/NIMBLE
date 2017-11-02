package com.example.shaur.nimblenavigationdrawer;

/**
 * Created by shaur on 02-11-2017.
 */

public class year_list {
    private String mlistyeartitle;
    private int mImageResourceId;

    public year_list(String vlistyeartile,int imageId)
    {
        mlistyeartitle=vlistyeartile;
        mImageResourceId=imageId;
    }

    public String getlisttitle(){
        return mlistyeartitle;
    }
    public int getmImageResourceId(){
        return mImageResourceId;
    }


}
