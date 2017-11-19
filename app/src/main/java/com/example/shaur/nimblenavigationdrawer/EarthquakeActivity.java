package com.example.shaur.nimblenavigationdrawer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earth>> {

    private EarthAdapter adapter;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5.5&limit=10";

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private TextView mEmptyStateText;
    @Override
    public Loader<List<Earth>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }


    @Override
    public void onLoadFinished(Loader<List<Earth>> loader, List<Earth> data) {
        //Hide the indicator once loading finishes
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        //Set empty tet to display "NO earthquakes Found"
        mEmptyStateText.setText(R.string.no_earthquakes);
        adapter.clear();
        if(data!=null && !data.isEmpty())
        {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earth>> loader) {
        adapter.clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);



        ListView listview = (ListView) findViewById(R.id.list);
        adapter = new EarthAdapter(this, new ArrayList<Earth>() );
        listview.setAdapter(adapter);

        //For listview we use onItemClickListner
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Earth currentdata = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentdata.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        mEmptyStateText=(TextView)findViewById(R.id.empty_view);
        listview.setEmptyView(mEmptyStateText);

        //Get a reference to the ConnectivityManager to check internet connection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connMgr.getActiveNetworkInfo();

        if(networkinfo!=null && networkinfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        }
        else
        {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            assert loadingIndicator != null;
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateText.setText(R.string.no_internet_conection);
        }

    }
}
